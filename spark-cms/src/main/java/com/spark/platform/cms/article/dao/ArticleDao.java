package com.spark.platform.cms.article.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.spark.platform.cms.article.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spark.platform.common.base.datasource.annotation.DataScope;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-05-02
 */
public interface ArticleDao extends BaseMapper<Article> {

    /**
     * 重写分页 添加数据权限
     * @param page
     * @param queryWrapper
     * @param <E>
     * @return
     */
    @Override
    @DataScope
    <E extends IPage<Article>> E selectPage(E page,@Param(Constants.WRAPPER) Wrapper<Article> queryWrapper);
}
