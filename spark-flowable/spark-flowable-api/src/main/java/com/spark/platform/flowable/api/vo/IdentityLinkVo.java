package com.spark.platform.flowable.api.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.api.vo
 * @ClassName: IdentityLinkVo
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/6/2 13:37
 * @Version: 1.0
 */
@Data
@ApiModel(value="IdentityLinkVo", description="参与人员")
public class IdentityLinkVo {
    private String url;
    private String user;
    private String group;
    private String type;

}
