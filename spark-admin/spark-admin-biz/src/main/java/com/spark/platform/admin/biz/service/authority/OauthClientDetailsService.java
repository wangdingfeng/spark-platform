package com.spark.platform.admin.biz.service.authority;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.spark.platform.admin.api.entity.authority.OauthClientDetails;
import com.spark.platform.admin.api.vo.SelectVo;

import java.util.List;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.service.authority
 * @ClassName: oauthClientDetailsService
 * @Description: Oauth 授权管理
 * @Version: 1.0
 */
public interface OauthClientDetailsService extends IService<OauthClientDetails> {

    /**
     * 根据clientId查询OauthClientDetails
     *
     * @param clientId clientId
     * @return OauthClientDetails
     */
    OauthClientDetails findOauthClientDetailsByClientId(String clientId);

    /**
     * 分页
     * @param oauthClientDetails
     * @param page
     * @return
     */
    IPage findPage(OauthClientDetails oauthClientDetails, Page page);

    /**
     * 保存更新
     * @param oauthClientDetails
     * @return
     */
    void insertOrUpdate(OauthClientDetails oauthClientDetails);

    /**
     * 获取所有的数据 select 插件使用
     * @return
     */
    List<SelectVo> selectElem();

}
