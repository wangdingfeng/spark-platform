package com.spark.platform.file.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spark.plateform.file.api.dto.FileInfoDTO;
import com.spark.plateform.file.api.dto.FileQueryDTO;
import com.spark.plateform.file.api.entity.FileInfo;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.file.biz.service.FileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 文件信息表 前端控制器
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/file-info")
@Api(tags = "文件上传")
@AllArgsConstructor
public class FileInfoController extends BaseController {

    private final FileInfoService fileInfoService;

    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传文件")
    public ApiResponse<FileInfo> upload(@RequestParam MultipartFile file) {
        return success(fileInfoService.upload(file));
    }

    @PatchMapping
    @ApiOperation(value = "绑定文件")
    public ApiResponse bind(@Valid @RequestBody FileInfoDTO fileInfoDTO) {
        fileInfoService.bindFile(fileInfoDTO);
        return success("绑定成功");
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public ApiResponse<IPage<FileInfo>> page(Page page, FileInfo fileInfo) {
        return success(fileInfoService.findPage(page, fileInfo));
    }

    @GetMapping("/biz")
    @ApiOperation(value = "业务查询")
    public ApiResponse<List<FileInfo>> findByBiz(FileQueryDTO fileQueryDTO) {
        return success(fileInfoService.findByBiz(fileQueryDTO));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID查询")
    public ApiResponse<FileInfo> getById(@PathVariable Long id) {
        return success(fileInfoService.getById(id));
    }

    @GetMapping("/api/{id}")
    @ApiOperation(value = "下载文件")
    public void download(@PathVariable Long id, HttpServletResponse response) {
        fileInfoService.downloadFile(id, response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除文件")
    public ApiResponse<Boolean> delete(@PathVariable Long id) {
        return success(fileInfoService.removeById(id));
    }

    @GetMapping("/url/{id}")
    @ApiOperation(value = "获取文件下载路径")
    public ApiResponse<String> url(@PathVariable Long id, Integer expires) {
        return success(fileInfoService.getDownloadURl(id, expires));
    }

}
