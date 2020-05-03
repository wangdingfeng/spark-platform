package com.spark.platform.cms.article.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cms_article")
@ApiModel(value="CmsArticle对象", description="文章")
public class Article extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "重要性")
    private Integer importance;

    @ApiModelProperty(value = "链接")
    private String link;

    @ApiModelProperty(value = "平台")
    private String platform;

    @ApiModelProperty(value = "是否原创")
    private String isOriginal;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "状态 0 暂存 1 退回修改 2 组长审核 3 主编审核 4 审核通过 5 审核不通过")
    private String status;

    @ApiModelProperty(value = "发布时间")
    private LocalDateTime publishTime;


}
