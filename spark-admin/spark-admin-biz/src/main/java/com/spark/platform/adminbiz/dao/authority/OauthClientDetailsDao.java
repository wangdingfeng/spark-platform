package com.spark.platform.adminbiz.dao.authority;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.adminapi.entity.authority.OauthClientDetails;
import org.springframework.stereotype.Repository;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.dao.authority
 * @ClassName: OauthClientDetailsDao
 * @Description: Oauth2 授权管理 clientId
 * @Version: 1.0
 */
@Repository
public interface OauthClientDetailsDao extends BaseMapper<OauthClientDetails> {

    /**
     * 根据clientId查询OauthClientDetails
     *
     * @param clientId clientId
     * @return OauthClientDetails
     */
    OauthClientDetails getOauthClientDetailsByClientId(String clientId);

    /**
     * 根据clientId查询resourceIds
     *
     * @param clientId clientId
     * @return String
     */
    String getResourceIdsByClientId(String clientId);

}
