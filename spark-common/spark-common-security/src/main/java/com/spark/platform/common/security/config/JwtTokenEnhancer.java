package com.spark.platform.common.security.config;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.security.config
 * @ClassName: JwtTokenEnhancer
 * @Description: 自定义token生成携带的信息
 * @Version: 1.0
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        // 给/oauth/token接口加属性roles,author
        JSONObject jsonObject = new JSONObject(authentication.getPrincipal());
        List<Object> authorities = jsonObject.getJSONArray("authorities").toList();
        List<String> roleList = Lists.newArrayList();
        for (Object authority : authorities) {
            Map map = (Map) authority;
            roleList.add((String)map.get("authority"));
        }
        String roles = StringUtils.join(roleList,",");
        additionalInfo.put("roles", roles);
        additionalInfo.put("author", "spark");
        additionalInfo.put("createTime", df.format(LocalDateTime.now()));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
