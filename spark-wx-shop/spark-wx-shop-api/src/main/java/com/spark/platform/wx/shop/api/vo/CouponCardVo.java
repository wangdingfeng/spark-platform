package com.spark.platform.wx.shop.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spark.platform.wx.shop.api.enums.CouponTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: wangdingfeng
 * @Date: 2021/1/9 15:10
 * @Description: 优惠券
 */
@Data
@ApiModel(value="CouponVo对象", description="优惠券Vo")
public class CouponCardVo {
    @ApiModelProperty(value = "主键")
    private Integer id;
    @ApiModelProperty(value = "优惠券名称")
    private String name;
    @ApiModelProperty(value = "优惠券类型 新人券 现金券 满减券 折扣券")
    private String couponType;
    @ApiModelProperty(value = "优惠券类型翻译")
    private String couponTypeName;
    @ApiModelProperty(value = "面额 优惠多少钱和打多少折")
    private BigDecimal denomination;
    @ApiModelProperty(value = "固定面额 即 大于多少开始优惠")
    private BigDecimal fixedDenomination;
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern="yyyy.MM.dd")
    private LocalDateTime startTime;
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern="yyyy.MM.dd")
    private LocalDateTime endTime;
    @ApiModelProperty(value = "是否限量")
    private Boolean isLimited;
    @ApiModelProperty(value = "发放总数")
    private Integer total;
    @ApiModelProperty(value = "剩余总量")
    private Integer lastTotal;

    public void setCouponType(String couponType) {
        this.couponType = couponType;
        this.couponTypeName = CouponTypeEnum.typeOf(couponType);
    }
}
