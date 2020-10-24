package com.spark.platform.admin.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.platform.admin.api.entity.gen.TableColumnInfo;
import com.spark.platform.admin.api.vo.GeneratorConfigVo;
import com.spark.platform.admin.biz.service.gen.GenService;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
@AllArgsConstructor
public class GenController extends BaseController {

    private final GenService genService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询数据表")
    public ApiResponse<IPage> page(Page page, String tableName){
        return success(genService.tableInfoPage(page,tableName));
    }

    @GetMapping("/column")
    @ApiOperation(value = "查询表字段信息")
    public ApiResponse<List<TableColumnInfo>> column(@RequestParam String tableName){
        return success(genService.findTableColumnInfo(tableName));
    }

    @GetMapping("/zip")
    @ApiOperation(value = "下载zip")
    public void download(GeneratorConfigVo generatorConfigVo, HttpServletResponse response){
        genService.downLoadCodes(generatorConfigVo,response);
    }
}
