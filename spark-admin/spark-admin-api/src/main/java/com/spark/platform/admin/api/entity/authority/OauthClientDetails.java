package com.spark.platform.admin.api.entity.authority;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.entity.authority
 * @ClassName: OauthClientDetails
 * @Description: 字段的具体使用 请参考 http://www.andaily.com/spring-oauth-server/db_table_description.html
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@TableName("sys_oauth_client_details")
@ApiModel(value = "OauthClientDetails",description = "Oauht2客户端详情设置")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "客户端(client)id")
    @NotBlank(message = "授权ID不能为空")
    private String clientId;

    @ApiModelProperty(value = "客户端所能访问的资源id集合")
    private String resourceIds;

    @ApiModelProperty(value = "客户端(client)的访问密匙")
    @NotBlank(message = "密钥不能为空")
    private String clientSecret;

    @ApiModelProperty(value = "客户端申请的权限范围")
    @NotBlank(message = "授权作用域不能为空")
    private String scope;

    @ApiModelProperty(value = "客户端支持的grant_type授权类型 password 密码模式")
    @NotBlank(message = "授权模式不能为空")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "重定向地址 逗号隔开")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "授权的权限")
    private String authorities;

    @ApiModelProperty(value = " token 时效")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "刷新时效")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "设置用户是否自动Approval操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'.")
    private String autoapprove;

}
