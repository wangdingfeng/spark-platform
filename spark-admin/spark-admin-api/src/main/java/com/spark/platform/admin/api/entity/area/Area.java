package com.spark.platform.admin.api.entity.area;

import com.baomidou.mybatisplus.annotation.TableName;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 行政区划
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_area")
@ApiModel(value="Area对象", description="行政区划")
public class Area extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域编码")
    private Integer id;

    @ApiModelProperty(value = "父级编号")
    private Integer parentId;

    @ApiModelProperty(value = "所有父级编号")
    private String parentIds;

    @ApiModelProperty(value = "本级排序号（升序）")
    private Integer sort;

    @ApiModelProperty(value = "层次级别")
    private Integer level;

    @ApiModelProperty(value = "全节点名")
    private String treeNames;

    @ApiModelProperty(value = "区域名称")
    private String areaName;

    @ApiModelProperty(value = "区域类型")
    private Integer areaType;

    /**
     * 判断是否有子节点
     * @return
     */
    public Boolean getHasChildren(){
        if(level == 2){
            return false;
        }
        return true;
    }


}
