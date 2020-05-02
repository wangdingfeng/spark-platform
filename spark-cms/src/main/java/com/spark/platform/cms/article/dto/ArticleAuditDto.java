package com.spark.platform.cms.article.dto;

import com.spark.platform.cms.article.entity.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: wangdingfeng
 * @Date: 2020/5/2 17:40
 * @Description: 审核dto
 */
@Data
@ApiModel(value="ArticleAuditDto", description="审核")
public class ArticleAuditDto {

    @ApiModelProperty(value = "文章主键")
    private Long articleId;

    @ApiModelProperty(value = "文章主键")
    private String taskId;

    @ApiModelProperty(value = "当前流程实例ID")
    private String processInstanceId;

    @ApiModelProperty(value = "当前流程节点key")
    private String taskDefinitionKey;

    @ApiModelProperty(value = "审核结果")
    private Boolean result;

    @ApiModelProperty(value = "批注信息")
    private String comment;

    @ApiModelProperty(value = "文章信息")
    private Article article;



}
