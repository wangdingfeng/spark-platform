package com.spark.platform.flowable.biz.service;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/5 13:40
 * @Description: 图片service
 */
public interface ImageService {
    /**
     * 根据流程实例标识，生成流程跟踪图示（高亮）
     *
     * @param procInstId 流程实例标识
     * @return
     * @throws Exception
     */
    byte[] generateImageByProcInstId(String procInstId) throws Exception;
}
