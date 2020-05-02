package com.spark.platform.cms.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.cms.article.dto.ArticleAuditDto;
import com.spark.platform.cms.article.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-05-02
 */
public interface ArticleService extends IService<Article> {

    /**
     * 分页
     * @param page
     * @param article
     * @return
     */
    IPage findPage(Page page, Article article);

    /**
     * 保存文章信息
     * @param article
     */
    void saveArticle(Article article);

    /**
     * 发布
     * @param article
     */
    void publish(Article article);

    /**
     * 退回修改
     * @param articleAuditDto
     */
    void backEdit(ArticleAuditDto articleAuditDto);

    /**
     * 审核
     * @param
     */
    void audit(ArticleAuditDto articleAuditDto);

}
