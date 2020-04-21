package com.spark.platform.flowable.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.flowable.api.DTO.DeploymentDTO;
import com.spark.platform.flowable.api.DTO.ProcessDefinitionDTO;
import com.spark.platform.flowable.api.vo.DeploymentVO;
import com.spark.platform.flowable.api.vo.ProcessDefinitionVO;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.engine.repository.*;
import org.springframework.lang.Nullable;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/4 21:46
 * @Description: 流程对象Service
 */
public interface ActProcessService {
    /**
     * 创建流程部署对象
     *
     * @return
     */
    DeploymentBuilder createDeployment();

    /**
     * 创建流程部署查询对象
     *
     * @return
     */
    DeploymentQuery createDeploymentQuery();

    /**
     * 创建流程定义查询对象
     *
     * @return
     */
    ProcessDefinitionQuery createProcessDefinitionQuery();

    /**
     * 部署流程定义
     *
     * @param url 流程定义文件URL
     * @return
     */
    Deployment deploy(String url);

    /**
     * 部署流程定义---通过inputstream流
     *
     * @param name     流程模板文件名字
     * @param tenantId 业务系统标识
     * @param category 流程模板文件类别
     * @param in       流程模板文件流
     * @return
     */
    DeploymentVO deploy(String name, @Nullable String tenantId, @Nullable String category, InputStream in);

    /**
     * 部署流程定义
     *
     * @param url    流程定义文件URL
     * @param pngUrl 流程定义文件pngUrl
     * @return
     */
    Deployment deploy(String url, String pngUrl);

    /**
     * 部署压缩包内的流程资源—
     * 资源包括：bpmn、png、drl、form等等
     * 流程引擎：内部使用迭代器方式遍历压缩包中文件并读取成响应的文件流
     *
     * @param zipInputStream
     * @param name           流程模板文件名字
     * @param tenantId       业务系统标识
     * @param category       流程模板文件类别
     * @return
     */
    DeploymentVO deploy(String name, String tenantId, String category, ZipInputStream zipInputStream);

    /**
     * 部署流程定义
     *
     * @param url    流程定义文件URL
     * @param drlUrl 规则引擎文件URL
     * @return
     */
    Deployment deployBpmnAndDrl(String url, String drlUrl);

    /**
     * 部署流程定义
     *
     * @param url      流程定义文件URL
     * @param name     流程定义名称
     * @param category 流程定义类别
     * @return
     */
    Deployment deploy(String url, String name, String category);

    /**
     * 部署流程定义
     *
     * @param url      流程定义文件URL
     * @param pngUrl   流程定义文件pngUrl
     * @param name     流程定义标识
     * @param category 流程定义类别
     * @return
     */
    Deployment deploy(String url, String pngUrl, String name, String category);

    /**
     * 根据流程定义key，判断流程定义（模板）是否已经部署过
     *
     * @param processDefinitionKey 流程定义key（即：流程模板ID）
     * @return
     */
    boolean exist(String processDefinitionKey);

    /**
     * 根据流程定义key，查询流程定义信息
     *
     * @param processDefinitionKey 流程定义key（即：流程模板ID）
     * @return
     */
    ProcessDefinition queryByProcessDefinitionKey(String processDefinitionKey);

    /**
     * 分页查询 流程定义信息
     * @param processDefinitionDTO 流程
     * @param page
     * @return
     */
    Page<ProcessDefinitionVO> listDefinitionPage(ProcessDefinitionDTO processDefinitionDTO, Page page);

    /**
     * 根据流程部署name，查询流程部署信息（最新）
     *
     * @param deploymentName 流程部署name
     * @return
     */
    Deployment deployName(String deploymentName);

    /**
     * 给流程定义授权用户
     *
     * @param processDefinitionKey 流程定义key
     * @param userId               流程定义key
     * @throws FlowableObjectNotFoundException 当流程定义或用户不存在时.
     */
    void addCandidateStarterUser(String processDefinitionKey, String userId);

    /**
     * 删除部署流程
     * @param deploymentId 部署id
     * @param cascade 是否递归删除
     */
    void deleteDeployment(String deploymentId, boolean cascade);

    /**
     * 分页查询
     * @param deploymentDTO 流程
     * @param page
     * @return
     */
    Page<DeploymentVO> listPage(DeploymentDTO deploymentDTO, Page page);

    /**
     * 读取资源
     * @param procDefId 流程定义ID
     * @param proInsId 流程实例ID
     * @param resType 资源类型(xml|image)
     * @return
     */
    InputStream resourceRead(String procDefId, String proInsId, String resType);
}
