package com.spark.platform.adminbiz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.adminapi.vo.GeneratorConfigVo;
import com.spark.platform.adminbiz.service.gen.GenService;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.controller
 * @ClassName: GenController
 * @Author: wangdingfeng
 * @Description: 代码生成器
 * @Date: 2020/4/15 14:29
 * @Version: 1.0
 */
@RestController
@RequestMapping("/gen")
@Api(tags = "代码生成器")
public class GenController extends BaseController {

    @Autowired
    private GenService genService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询数据表")
    public ApiResponse page(Page page, String tableName){
        return success(genService.tableInfoPage(page,tableName));
    }

    @GetMapping("/column")
    @ApiOperation(value = "查询表字段信息")
    public ApiResponse column(@RequestParam String tableName){
        return success(genService.findTableColumnInfo(tableName));
    }

    @GetMapping("/zip")
    @ApiOperation(value = "下载zip")
    public void download(GeneratorConfigVo generatorConfigVo, HttpServletResponse response){
        genService.downLoadCodes(generatorConfigVo,response);
    }
}
