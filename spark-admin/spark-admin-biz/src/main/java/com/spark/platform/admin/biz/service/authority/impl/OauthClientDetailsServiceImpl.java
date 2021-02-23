package com.spark.platform.admin.biz.service.authority.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spark.platform.admin.api.vo.SelectVo;
import com.spark.platform.admin.biz.service.authority.OauthClientDetailsService;
import com.spark.platform.admin.api.entity.authority.OauthClientDetails;
import com.spark.platform.admin.biz.dao.authority.OauthClientDetailsDao;
import com.spark.platform.common.base.constants.RedisConstants;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.common.base.support.WrapperSupport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @CacheEvict(value = RedisConstants.CLIENT_CACHE,key = "#oauthClientDetails.clientId")
    public void insertOrUpdate(OauthClientDetails oauthClientDetails) {
        validateClientId(oauthClientDetails.getClientId(),oauthClientDetails.getId());
        super.saveOrUpdate(oauthClientDetails);
    }

    @Override
    public List<SelectVo> selectElem() {
        return super.baseMapper.selectElem();
    }

    /**
     * 验证 clientId
     * @param clientId
     * @param id
     */
    private void validateClientId(String clientId, Long id) {
        LambdaQueryWrapper<OauthClientDetails> queryWrapper = Wrappers.lambdaQuery();
        if (null != id) {
            queryWrapper.ne(OauthClientDetails::getId, id);
        }
        queryWrapper.eq(OauthClientDetails::getClientId, StringUtils.trim(clientId));
        if (0 != super.count(queryWrapper)) {
            throw new BusinessException("clientId不允许重复");
        }
    }
}