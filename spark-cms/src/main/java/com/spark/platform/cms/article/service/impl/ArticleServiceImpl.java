package com.spark.platform.cms.article.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.spark.platform.cms.article.constant.ArticleConstant;
import com.spark.platform.cms.article.constant.ArticleProcessKeyNode;
import com.spark.platform.cms.article.dto.ArticleAuditDto;
import com.spark.platform.cms.article.entity.Article;
import com.spark.platform.cms.article.dao.ArticleDao;
import com.spark.platform.cms.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.common.base.enums.SparkHttpStatus;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.WrapperSupport;
import com.spark.platform.common.security.util.UserUtils;
import com.spark.platform.flowable.api.enums.ActionEnum;
import com.spark.platform.flowable.api.enums.VariablesEnum;
import com.spark.platform.flowable.api.feign.client.InstanceClient;
import com.spark.platform.flowable.api.feign.client.TaskClient;
import com.spark.platform.flowable.api.request.ExecuteTaskRequest;
import com.spark.platform.flowable.api.request.ProcessInstanceCreateRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-05-02
 */
@Service
@Slf4j
@AllArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

    private final InstanceClient instanceClient;

    private final TaskClient taskClient;

    @Override
    public IPage findPage(Page page, Article article) {
        QueryWrapper wrapper = new QueryWrapper<Article>();
        wrapper.orderByDesc("modify_date");
        WrapperSupport.putParamsLike(wrapper,article,"title","author");
        WrapperSupport.putParamsEqual(wrapper,article,"platforms","status");
        return super.baseMapper.selectPage(page,wrapper);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveArticle(Article article) {
        article.setPublishTime(ArticleConstant.STATUS_APPROVAL_GROUP.equals(article.getStatus())?LocalDateTime.now():null);
        super.saveOrUpdate(article);
        //判断是否发布
        if(ArticleConstant.STATUS_APPROVAL_GROUP.equals(article.getStatus())){
            publish(article);
        }
    }

    @Override
    public void publish(Article article) {
        //放入提交人
        Map<String,Object> variables = ImmutableMap.of(VariablesEnum.submitter.toString(), UserUtils.getLoginUser().getUsername());
        ProcessInstanceCreateRequest request = new ProcessInstanceCreateRequest(ArticleConstant.PROCESS_KEY,article.getId().toString(),ArticleConstant.PROCESS_BUSINESS_TYPE,article.getTitle(),"",variables);
        ApiResponse apiResponse = instanceClient.startByKey(request);
        if(!SparkHttpStatus.SUCCESS.getCode().equals(apiResponse.getCode())){
            log.error("发起工作流失败",apiResponse.getData());
            throw new BusinessException("发起工作流失败!");
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void backEdit(ArticleAuditDto articleAuditDto) {
        Article article = articleAuditDto.getArticle();
        article.setStatus(articleAuditDto.getResult() == true ? ArticleConstant.STATUS_APPROVAL_GROUP :ArticleConstant.STATUS_FAIL);
        super.updateById(article);
        //执行工作流
        ArticleProcessKeyNode keyNode = ArticleProcessKeyNode.getProcessNextKeyNodeByKey(articleAuditDto.getTaskDefinitionKey(),articleAuditDto.getResult());
        Map<String,Object> variables = ImmutableMap.of(ArticleConstant.PROCESS_NODE_SUBMIT_APPROVAL.toUpperCase()+ArticleConstant.SUBMIT_SUFFIX,keyNode.getTargetNode());
        ExecuteTaskRequest executeTaskRequest = ExecuteTaskRequest.builder().action(ActionEnum.COMPLETE.getAction()).variables(variables).build();
        executeTask(articleAuditDto.getTaskId(),executeTaskRequest);
    }

    @Override
    @Transactional(readOnly = false)
    public void audit(ArticleAuditDto articleAuditDto) {
        Assert.notBlank(articleAuditDto.getTaskId(),"任务ID不能为空");
        Assert.notNull(articleAuditDto.getArticleId(),"文章id不能为空");
        Assert.notBlank(articleAuditDto.getTaskDefinitionKey(),"当前流程节点id不能为空");
        Article article = new Article();
        article.setId(articleAuditDto.getArticleId());
        Map<String,Object> variables = Maps.newHashMap();
        Boolean result = articleAuditDto.getResult();
        switch (articleAuditDto.getTaskDefinitionKey()){
            case ArticleConstant.PROCESS_NODE_GROUP_LEADER_APPROVE:
                //组长审核
                ArticleProcessKeyNode keyNode = ArticleProcessKeyNode.getProcessNextKeyNodeByKey(articleAuditDto.getTaskDefinitionKey(),articleAuditDto.getResult());
                variables.put(ArticleConstant.PROCESS_NODE_GROUP_LEADER_APPROVE.toUpperCase()+ArticleConstant.SUBMIT_SUFFIX,keyNode.getTargetNode());
                if(result){
                    //审核通过
                    article.setStatus(ArticleConstant.STATUS_APPROVAL_EDITOR);
                    variables.put(ArticleConstant.PROCESS_VARIABLES_KEY_EDIT1,"zhubian1");
                    variables.put(ArticleConstant.PROCESS_VARIABLES_KEY_EDIT2,"zhubian2");
                }else{
                    article.setStatus(ArticleConstant.STATUS_BACK_EDIT);
                }

                break;
            case ArticleConstant.PROCESS_NODE_EDITOR1_APPROVAL:
                //主编1 审核
                variables.put(ArticleConstant.MULTIINSTANCE_RESULT_PREFIX+"editor1",articleAuditDto.getResult());
                break;
            case ArticleConstant.PROCESS_NODE_EDITOR2_APPROVAL:
                //主编1 审核
                variables.put(ArticleConstant.MULTIINSTANCE_RESULT_PREFIX+"editor2",articleAuditDto.getResult());
                break;
            default:
                break;
        }
        super.updateById(article);
        if(StringUtils.isNotBlank(articleAuditDto.getComment())){
            //保存审核信息
            ApiResponse apiResponse = taskClient.addComments(articleAuditDto.getTaskId(),articleAuditDto.getProcessInstanceId(),articleAuditDto.getComment(),UserUtils.getLoginUser().getUsername());
            if(!SparkHttpStatus.SUCCESS.getCode().equals(apiResponse.getCode())){
                log.error("添加批注失败",apiResponse.getData());
                throw new BusinessException("添加批注失败!");
            }
        }
        //执行工作流
        ExecuteTaskRequest executeTaskRequest = ExecuteTaskRequest.builder().action(ActionEnum.COMPLETE.getAction()).variables(variables).localScope(false).build();
        executeTask(articleAuditDto.getTaskId(),executeTaskRequest);
    }

    /**
     * 执行工作流
     * @param executeTaskRequest
     */
    private void executeTask(String taskId,ExecuteTaskRequest executeTaskRequest){
        executeTaskRequest.setLocalScope(false);
        ApiResponse apiResponse = taskClient.executeTask(taskId, executeTaskRequest);
        if(!SparkHttpStatus.SUCCESS.getCode().equals(apiResponse.getCode())){
            log.error("执行工作流失败",apiResponse.getData());
            throw new BusinessException("执行工作流失败!");
        }
    }
}
