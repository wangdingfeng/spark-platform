package com.spark.platform.wx.shop.biz.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.wx.shop.api.dto.ShopGoodsQueryDTO;
import com.spark.platform.wx.shop.api.vo.GoodsCardVo;
import com.spark.platform.wx.shop.api.vo.GoodsCategoryVo;
import com.spark.platform.wx.shop.api.vo.GoodsDetailVo;
import com.spark.platform.wx.shop.biz.api.service.ApiGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2021/1/8 23:09
 * @Description:
 */
@RestController
@RequestMapping("/api")
@Api(tags = "小程序商品接口")
@RequiredArgsConstructor
public class ApiGoodsController extends BaseController {

    private final ApiGoodsService apiGoodsService;

    @GetMapping("/goods/page")
    @ApiOperation(value = "商品分页")
    public ApiResponse<IPage<GoodsCardVo>> page(ShopGoodsQueryDTO shopGoodsQueryDTO) {
        return success(apiGoodsService.list(shopGoodsQueryDTO));
    }

    @GetMapping("/goods/{goodId}")
    @ApiOperation(value = "获取商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id"),
            @ApiImplicitParam(name = "goodId", value = "商品Id", required = true)
    })
    public ApiResponse<GoodsDetailVo> detail(@PathVariable Integer goodId, @RequestParam(required = false) Integer userId) {
        return success(apiGoodsService.detail(userId, goodId));
    }

    @GetMapping("/goods/related")
    @ApiOperation(value = "个性推荐")
    public ApiResponse<List<GoodsCardVo>> related(@RequestParam Integer goodId) {
        return success(apiGoodsService.related(goodId));
    }

    @GetMapping("/goods/category")
    @ApiOperation(value = "查询分类")
    @ApiImplicitParam(name = "level", value = "最大层级")
    public ApiResponse<List<GoodsCategoryVo>> category(@RequestParam Integer level) {
        return success(apiGoodsService.categoryTree(level));
    }

    @GetMapping("/goods/comment/{goodsId}")
    @ApiOperation(value = "查询商品评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "页数",defaultValue = "20"),
            @ApiImplicitParam(name = "current", value = "页码",defaultValue = "1"),
            @ApiImplicitParam(name = "goodId", value = "商品Id", required = true)
    })
    public ApiResponse<List<GoodsCategoryVo>> comment(@PathVariable Integer goodsId,@RequestParam Long size,@RequestParam Long current) {
        return success(apiGoodsService.pageGoodsComment(size,current,goodsId));
    }


}
