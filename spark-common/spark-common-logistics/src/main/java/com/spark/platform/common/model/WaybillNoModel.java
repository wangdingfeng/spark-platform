package com.spark.platform.common.model;

import lombok.Data;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.model
 * @ClassName: WaybillNoModel
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2021/1/14 9:20
 * @Version: 1.0
 */
@Data
public class WaybillNoModel {
    /**
     * 运单号
     */
    private String waybillNo;
    /**
     * 走件产生时间 yyyy/MM/dd HH:mm:ss
     */
    private String uploadTime;
    /**
     * 物流状态，固定为：GOT 已收件;ARRIVAL 已收入;DEPARTURE 已发出;PACKAGE 已打包;SENT_SCAN 派件;INBOUND 自提柜入柜;SIGNED 签收成功;FAILED 签收失败
     */
    private String status;
    /**
     * 物流信息
     */
    private String content;

}
