package com.spark.platform.wx.shop.api.entity.order;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单物流信息表，发货时生成
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-21
 */
@Data
@Accessors(chain = true)
@TableName("shop_order_express")
@ApiModel(value="ShopOrderExpress对象", description="订单物流信息表，发货时生成")
public class ShopOrderExpress {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "订单ID")
    private Integer orderId;

    @ApiModelProperty(value = "运费ID")
    private Integer shipperId;

    @ApiModelProperty(value = "物流公司名称")
    private String shipperName;

    @ApiModelProperty(value = "物流公司代码")
    private String shipperCode;

    @ApiModelProperty(value = "快递单号")
    private String logisticCode;

    @ApiModelProperty(value = "物流跟踪信息")
    private String traces;

    @ApiModelProperty(value = "是否完成")
    private Boolean isFinish;

    @ApiModelProperty(value = "总查询次数")
    private Integer requestCount;

    @ApiModelProperty(value = "最近一次向第三方查询物流信息时间")
    private LocalDateTime requestTime;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @TableField(value = "modify_date", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyDate;


}
