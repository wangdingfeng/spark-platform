package com.spark.platform.cms.article.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.cms.article.dto.ArticleAuditDto;
import com.spark.platform.cms.article.entity.Article;
import com.spark.platform.cms.article.service.ArticleService;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.spark.platform.common.base.support.BaseController;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-05-02
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章ArticleController")
@AllArgsConstructor
public class ArticleController extends BaseController {

    private final ArticleService articleService;


    @PostMapping("/page")
    @ApiOperation(value = "分页查询")
    public ApiResponse page(Article article, Page page){
        return success(articleService.findPage(page,article));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询")
    public ApiResponse findById(@PathVariable Long id){
        return success(articleService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "保存信息")
    public ApiResponse save(@RequestBody Article article){
        articleService.saveArticle(article);
        return success("操作成功");
    }

    @PutMapping
    @ApiOperation(value = "发布")
    public ApiResponse publish(@RequestBody Article article){
        articleService.publish(article);
        return success("操作成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public ApiResponse delete(@PathVariable Long id){
        return success(articleService.removeById(id));
    }

    @PostMapping("/audit")
    @ApiOperation(value = "审核")
    public ApiResponse audit(@RequestBody ArticleAuditDto articleAuditDto){
        articleService.audit(articleAuditDto);
        return success("操作成功");
    }

    @PostMapping("/back")
    @ApiOperation(value = "退回修改")
    public ApiResponse backSubmit(@RequestBody ArticleAuditDto articleAuditDto){
        articleService.backEdit(articleAuditDto);
        return success("操作成功");
    }

}
