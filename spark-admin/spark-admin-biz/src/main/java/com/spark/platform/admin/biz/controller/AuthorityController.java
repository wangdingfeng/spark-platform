package com.spark.platform.admin.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.admin.api.entity.authority.OauthClientDetails;
import com.spark.platform.admin.api.vo.SelectVo;
import com.spark.platform.admin.biz.service.authority.OauthClientDetailsService;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: wangdingfeng
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.controller
 * @ClassName: AuthorityController
 * @Date: 2019/9/28 13:59
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/authority")
@Api(tags = "Oauht2客户端")
@RequiredArgsConstructor
public class AuthorityController extends BaseController {

    private final OauthClientDetailsService oauthClientDetailsService;


    @GetMapping("/api/info")
    @ApiOperation(value = "根据clientId获取认证客户端详情信息")
    public ApiResponse<OauthClientDetails> getOauthClientDetailsByClientId(@RequestParam String clientId) {
        return success(oauthClientDetailsService.findOauthClientDetailsByClientId(clientId));
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public ApiResponse<IPage> page(OauthClientDetails oauthClientDetails, Page page){
        return success(oauthClientDetailsService.findPage(oauthClientDetails,page));
    }

    @PostMapping
    @ApiOperation(value = "保存信息")
    @PreAuthorize("hasAnyAuthority('oauth:add')")
    public ApiResponse<OauthClientDetails> save(@RequestBody @Valid OauthClientDetails oauthClientDetails){
        oauthClientDetailsService.insertOrUpdate(oauthClientDetails);
        return success(oauthClientDetails);
    }

    @PutMapping
    @ApiOperation(value = "更新信息")
    @PreAuthorize("hasAnyAuthority('oauth:edit')")
    public ApiResponse<OauthClientDetails> update(@RequestBody @Valid OauthClientDetails oauthClientDetails){
        oauthClientDetailsService.insertOrUpdate(oauthClientDetails);
        return success(oauthClientDetails);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAnyAuthority('oauth:delete')")
    public ApiResponse<Boolean> delete(@PathVariable Long id){
        return success(oauthClientDetailsService.removeById(id));
    }

    @GetMapping("/select")
    @ApiOperation(value = "获取认证客户端下拉框数据")
    public ApiResponse<SelectVo> select(){
        return success(oauthClientDetailsService.selectElem());
    }


}
