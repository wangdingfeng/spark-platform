package com.spark.platform.wx.shop.biz.goods.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsComment;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsCommentService;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.spark.platform.common.base.support.BaseController;

/**
 * <p>
 * 商品评价 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-18
 */
@RestController
@RequestMapping("/goods/comment")
@Api(tags = "商品评价")
@AllArgsConstructor
public class ShopGoodsCommentController extends BaseController {

    private final ShopGoodsCommentService shopGoodCommentService;

    @GetMapping("/page")
    @ApiOperation(value = "商品评价列表")
    public ApiResponse page(ShopGoodsComment shopGoodComment, Page page) {
        return success(shopGoodCommentService.listPage(page, shopGoodComment));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除商品评价")
    public ApiResponse delete(@PathVariable Long id) {
        return success(shopGoodCommentService.removeById(id));
    }

}
