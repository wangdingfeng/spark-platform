package com.spark.platform.common.security.service;

import com.spark.platform.common.base.constants.RedisConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @Author: wangdingfeng
 * @Description: 重写查询 oauth_client_details
 * @Date: 2020/12/8 9:53
 */
@Slf4j
public class SparkClientDetailsService extends JdbcClientDetailsService {
    /**
     * 查询 客户端sql {noop} 表示从数据库中读取密码 不用加密
     */
    private final String selectClientDetailsSql = "select client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove from sys_oauth_client_details where client_id = ?";

    public SparkClientDetailsService(DataSource dataSource) {
        super(dataSource);
        super.setSelectClientDetailsSql(selectClientDetailsSql);
    }

    @Override
    @Cacheable(value = RedisConstants.CLIENT_CACHE, unless = "#result == null", key = "#clientId")
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        log.info("当前clientId：{}", clientId);
        return super.loadClientByClientId(clientId);
    }
}
