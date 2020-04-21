package com.spark.platform.adminapi.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/18 13:39
 * @Description:
 */
@Data
public class FileInfoDTO {
    /**
     * 文件id
     */
    private List<Long> fileIds;
    /**
     * 删除文件id
     */
    private List<Long> deleteFileIds;
    /**
     * 业务id
     */
    private String bizId;
    /**
     * 业务类型
     */
    private String bizType;
}
