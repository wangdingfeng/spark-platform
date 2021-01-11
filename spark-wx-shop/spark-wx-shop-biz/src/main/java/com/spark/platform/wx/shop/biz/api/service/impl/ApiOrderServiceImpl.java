package com.spark.platform.wx.shop.biz.api.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.spark.platform.common.base.exception.BusinessException;
import com.spark.platform.wx.shop.api.dto.SubmitOrderDTO;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoods;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;
import com.spark.platform.wx.shop.api.entity.marketing.ShopCouponUser;
import com.spark.platform.wx.shop.api.entity.marketing.ShopPinkGoods;
import com.spark.platform.wx.shop.api.entity.marketing.ShopSeckillGoods;
import com.spark.platform.wx.shop.api.entity.order.ShopOrder;
import com.spark.platform.wx.shop.api.entity.order.ShopOrderGoods;
import com.spark.platform.wx.shop.api.entity.user.ShopUserAddress;
import com.spark.platform.wx.shop.api.enums.CouponTypeEnum;
import com.spark.platform.wx.shop.api.enums.CouponUserStatusEnum;
import com.spark.platform.wx.shop.api.enums.ShopGoodsStatusEnum;
import com.spark.platform.wx.shop.api.enums.ShopOrderStatusEnum;
import com.spark.platform.wx.shop.biz.api.service.ApiOrderService;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsService;
import com.spark.platform.wx.shop.biz.goods.service.ShopGoodsSkuService;
import com.spark.platform.wx.shop.biz.marketing.service.ShopCouponUserService;
import com.spark.platform.wx.shop.biz.marketing.service.ShopPinkGoodsService;
import com.spark.platform.wx.shop.biz.marketing.service.ShopSeckillGoodsService;
import com.spark.platform.wx.shop.biz.order.service.ShopOrderService;
import com.spark.platform.wx.shop.biz.user.service.ShopUserAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.biz.api.service.impl
 * @ClassName: ApiOrderServiceImpl
 * @Author: wangdingfeng
 * @Description: 订单
 * @Date: 2020/12/23 15:31
 * @Version: 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApiOrderServiceImpl implements ApiOrderService {

    private final ShopSeckillGoodsService shopSeckillGoodsService;
    private final ShopPinkGoodsService shopPinkGoodsService;
    private final ShopUserAddressService shopUserAddressService;
    private final ShopGoodsService goodsService;
    private final ShopGoodsSkuService shopGoodsSkuService;
    private final ShopCouponUserService shopCouponUserService;
    private final ShopOrderService orderService;

    @Override
    @Transactional(readOnly = false)
    public boolean submit(SubmitOrderDTO submitOrderDTO) {
        BigDecimal goodsPrice = BigDecimal.ZERO;
        BigDecimal couponAmout;
        // 校验活动状态
        this.validSeckillGoods(submitOrderDTO);
        this.validPinkGoods(submitOrderDTO);

        // 收货地址
        ShopUserAddress userAddress = shopUserAddressService.getById(submitOrderDTO.getAddressId());
        Assert.notNull(userAddress,"提交订单失败:请添加收货地址信息！");

        List<ShopOrderGoods> shopOrderGoodsList = new ArrayList<>(submitOrderDTO.getOrderGoods().size());
        for(SubmitOrderDTO.OrderGoods orderGoods : submitOrderDTO.getOrderGoods()){
            // 查询商品信息
            ShopGoods goods = goodsService.getById(orderGoods.getGoodsId());
            Assert.notNull(goods,"提交订单失败:当前商品已经不存在！");
            Assert.isTrue(!ShopGoodsStatusEnum.PUBLISH.getDesc().equals(goods.getStatus()),"提交订单失败:当前商品已经下架！");
            // 查询商品的规格信息
            ShopGoodsSku goodsSku = shopGoodsSkuService.findByGoodsIdAndVals(orderGoods.getGoodsId(),orderGoods.getGoodsAttrValIds());
            Assert.notNull(goodsSku,"提交订单失败:当前商品规格已经下架，请重新选择！");
            goodsPrice = goodsPrice.add(orderGoods.getPrice().multiply(new BigDecimal(orderGoods.getNumber())));
            // 修改库存 计算总库存
            Boolean flag = shopGoodsSkuService.subStock(goodsSku.getId(),orderGoods.getNumber());
            Assert.isTrue(flag,String.format("当前商品:%s库存不足,可用库存:%s,下单库存:%s",goods.getTitle(),goodsSku.getStock(),orderGoods.getNumber()));
            goodsService.calTotalStock(goods.getId());
            // 保存订单商品信息
            ShopOrderGoods shopOrderGoods = new ShopOrderGoods();
            shopOrderGoods.setGoodsId(goods.getId());
            shopOrderGoods.setGoodsTitle(goods.getTitle());
            shopOrderGoods.setGoodsSn(goods.getGoodsSn());
            shopOrderGoods.setPicUrl(goods.getHomePic());
            shopOrderGoods.setGoodsAttrValIds(orderGoods.getGoodsAttrValIds());
            shopOrderGoods.setGoodsAttrVals(goodsSku.getAttrVals());
            shopOrderGoods.setNumber(orderGoods.getNumber());
            shopOrderGoods.setPrice(orderGoods.getPrice());
            shopOrderGoods.setTotalAmount(orderGoods.getPrice().multiply(new BigDecimal(orderGoods.getNumber())));
            shopOrderGoodsList.add(shopOrderGoods);
        }
        // 获取优惠券金额
        couponAmout = getCouponAmount(submitOrderDTO,goodsPrice);
        // 保存订单详情
        ShopOrder shopOrder = new ShopOrder();
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        shopOrder.setOrderSn(String.valueOf(snowflake.nextId()));
        shopOrder.setUserId(submitOrderDTO.getUserId());
        shopOrder.setGoodsPrice(goodsPrice);
        // 订单总价= 商品总价 + 运费 +配送费 (运费、配送费先不考虑)
        shopOrder.setOrderPrice(goodsPrice);
        // 实际支付的费用 订单总价 - 优惠金额
        shopOrder.setActualPrice(goodsPrice.subtract(couponAmout));
        shopOrder.setOrderType(submitOrderDTO.getOrderType());
        shopOrder.setOrderStatus(ShopOrderStatusEnum.WAIT_PAY.getStatus());
        shopOrder.setConsignee(userAddress.getName());
        shopOrder.setProvince(userAddress.getProvince());
        shopOrder.setCity(userAddress.getCity());
        shopOrder.setDistrict(userAddress.getArea());
        shopOrder.setAddress(userAddress.getAddress());
        shopOrder.setMobile(userAddress.getMobile());
        shopOrder.setUserRemarks(submitOrderDTO.getUserRemarks());
        shopOrder.setGoodsList(shopOrderGoodsList);
        return orderService.saveOrder(shopOrder);
    }

    /**
     * 校验秒杀产品
     * @param submitOrderDTO
     */
    private void validSeckillGoods(SubmitOrderDTO submitOrderDTO){
        // 判断商品的活动状态
        if(null != submitOrderDTO.getSeckillGoodsId()){
            // 是否是秒杀的产品
            ShopSeckillGoods shopSeckillGoods = shopSeckillGoodsService.getById(submitOrderDTO.getSeckillGoodsId());
            Assert.notNull(shopSeckillGoods,"提交订单失败，查询不到当前活动产品信息！");
            if(shopSeckillGoods.getEndTime().isAfter(LocalDateTime.now())){
                throw new BusinessException("当前商品已过活动时间！");
            }
        }
    }

    /**
     * 校验团购产品
     * @param submitOrderDTO
     */
    private void validPinkGoods(SubmitOrderDTO submitOrderDTO){
        // 判断商品的活动状态
        if(null != submitOrderDTO.getPinkGoodsId()){
            // 是否是团购商品
            ShopPinkGoods shopPinkGoods = shopPinkGoodsService.getById(submitOrderDTO.getPinkGoodsId());
            Assert.notNull(shopPinkGoods,"提交订单失败:查询不到当前活动产品信息！");
            if(shopPinkGoods.getEndTime().isAfter(LocalDateTime.now())){
                throw new BusinessException("当前商品已过活动时间！");
            }
        }
    }

    /**
     * 校验优惠券
     * @param submitOrderDTO
     */
    private BigDecimal getCouponAmount(SubmitOrderDTO submitOrderDTO,BigDecimal goodsPrice){
        BigDecimal couponAmout = BigDecimal.ZERO;
        // 获取优惠券金额
        if(null != submitOrderDTO.getCouponUserId()){
            // 查询优惠券信息
            ShopCouponUser couponUser = shopCouponUserService.findById(submitOrderDTO.getCouponUserId());
            Assert.notNull(couponUser,"提交订单失败，当前优惠券信息错误！");
            Assert.isTrue(CouponUserStatusEnum.NO_USE.getStatus().equals(couponUser.getStatus()),"提交订单失败，当前优惠券不可用！");
            Assert.isTrue(couponUser.getEndTime().isBefore(LocalDateTime.now()),"提交订单失败，当前优惠券已过期！");
            // 获取优惠券的类型
            if(CouponTypeEnum.DISCOUNT.getType().equals(couponUser.getCoupon().getCouponType())){
                // 如果是折扣券
                couponAmout = goodsPrice.multiply(couponUser.getCoupon().getDenomination());
            }else if(CouponTypeEnum.FULLRED.getType().equals(couponUser.getCoupon().getCouponType())){
                Assert.isTrue(goodsPrice.compareTo(couponUser.getCoupon().getFixedDenomination()) == -1,"提交订单失败，当前总金额额不满足满减优惠金额！");
                couponAmout = couponUser.getCoupon().getDenomination();
            }else {
                couponAmout = couponUser.getCoupon().getDenomination();
            }
        }
        return couponAmout;
    }
}
