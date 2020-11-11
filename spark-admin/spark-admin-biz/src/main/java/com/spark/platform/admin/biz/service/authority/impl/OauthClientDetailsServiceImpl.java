package com.spark.platform.admin.biz.service.authority.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.admin.biz.service.authority.OauthClientDetailsService;
import com.spark.platform.admin.api.entity.authority.OauthClientDetails;
import com.spark.platform.admin.biz.dao.authority.OauthClientDetailsDao;
import com.spark.platform.common.base.constants.RedisConstants;
import com.spark.platform.common.base.support.WrapperSupport;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.authority.impl
 * @ClassName: OauthClientDetailsServiceImpl
 * @Description: Oauth 授权管理
 * @Version: 1.0
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsDao, OauthClientDetails> implements OauthClientDetailsService {

    @Override
    @Cacheable(value= RedisConstants.CLIENT_CACHE,unless = "#result == null", key="T(com.spark.platform.common.base.constants.RedisConstants).CLIENT_DETAILS_KEY.concat(T(String).valueOf(#clientId))")
    public OauthClientDetails findOauthClientDetailsByClientId(String clientId) {
        return this.baseMapper.getOauthClientDetailsByClientId(clientId);
    }

    @Override
    public IPage findPage(OauthClientDetails oauthClientDetails, Page page) {
        QueryWrapper wrapper = new QueryWrapper<OauthClientDetails>();
        WrapperSupport.putParamsEqual(wrapper,oauthClientDetails,"clientId");
        return super.page(page,wrapper);
    }

    @Override
    @CacheEvict(value = RedisConstants.CLIENT_CACHE, allEntries = true)
    public void insertOrUpdate(OauthClientDetails oauthClientDetails) {
        super.saveOrUpdate(oauthClientDetails);
    }
}