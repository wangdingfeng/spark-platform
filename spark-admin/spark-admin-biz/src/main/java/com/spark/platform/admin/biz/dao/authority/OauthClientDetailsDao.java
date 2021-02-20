package com.spark.platform.admin.biz.dao.authority;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.admin.api.entity.authority.OauthClientDetails;
import com.spark.platform.admin.api.vo.SelectVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * 获取下拉列表数据
     * @return
     */
    @Select("SELECT id AS 'value',client_id AS 'label' FROM sys_oauth_client_details WHERE del_flag='0'")
    List<SelectVo> selectElem();

}
