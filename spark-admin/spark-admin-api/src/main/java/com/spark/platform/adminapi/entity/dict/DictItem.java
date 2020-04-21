package com.spark.platform.adminapi.entity.dict;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spark.platform.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.entity.dict
 * @ClassName: DictItem
 * @Author: wangdingfeng
 * @Description: 字典子列表
 * @Date: 2020/3/26 9:57
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_dict_item")
@ApiModel(value = "DictItem",description = "字典子项设置")
public class DictItem extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 字典标签值
     */
    private String label;

    /**
     * 字典值
     */
    private String value;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;
}
