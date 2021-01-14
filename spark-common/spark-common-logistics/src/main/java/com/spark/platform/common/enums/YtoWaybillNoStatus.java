package com.spark.platform.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.yto.model
 * @ClassName: WaybillNoStatus
 * @Author: wangdingfeng
 * @Description: 圆通物流单号状态
 * @Date: 2021/1/13 18:29
 * @Version: 1.0
 */
@AllArgsConstructor
@Getter
public enum YtoWaybillNoStatus {

    GOT("GOT","已收件"),
    ARRIVAL("ARRIVAL","已收入"),
    DEPARTURE("DEPARTURE","已发出"),
    PACKAGE("PACKAGE","已打包"),
    SENT_SCAN("SENT_SCAN","派件"),
    INBOUND("INBOUND","自提柜入柜"),
    SIGNED("SIGNED","签收成功"),
    FAILED("FAILED"," 签收失败");

    private String status;

    private String desc;


    /**
     * 状态类型
     * @param status
     * @return
     */
    public static String statusOf(String status) {
        for(YtoWaybillNoStatus actionEnum : values()){
            if(actionEnum.getStatus().equals(status)){
                return actionEnum.desc;
            }
        }
        return null;
    }
}
