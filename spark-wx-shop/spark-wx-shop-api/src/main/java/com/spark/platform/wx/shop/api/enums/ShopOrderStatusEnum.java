package com.spark.platform.wx.shop.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.enums
 * @ClassName: ShopOrderStatusEnum
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/7 14:29
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ShopOrderStatusEnum {

    WAIT_PAY(0,"待付款"),
    CANCEL(1,"已取消"),
    PAY(2,"已付款"),
    SEND(3,"已发货"),
    REFUND(4,"退款"),
    COMPLETE(5,"完成"),
    EVALUATION(6,"待评价");

    private Integer status;
    private String desc;

    /**
     * 操作类型
     * @param status
     * @return
     */
    public static ShopOrderStatusEnum statusOf(Integer status) {
        for(ShopOrderStatusEnum statusEnum : values()){
            if(statusEnum.getStatus().equals(status)){
                return statusEnum;
            }
        }
        throw new RuntimeException("没有找到对应的枚举");
    }
}
