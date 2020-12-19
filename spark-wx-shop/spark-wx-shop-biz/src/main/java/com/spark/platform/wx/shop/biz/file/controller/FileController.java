package com.spark.platform.wx.shop.biz.file.controller;

import cn.hutool.core.lang.UUID;
import com.spark.platform.common.base.support.ApiResponse;
import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.common.config.properties.QiNiuProperties;
import com.spark.platform.common.config.properties.SparkProperties;
import com.spark.platform.wx.shop.biz.utils.QiNiuUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.biz.file.controller
 * @ClassName: FileController
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/12/17 17:08
 * @Version: 1.0
 */
@RestController
@RequestMapping("/file")
@Api(tags = "上传图片")
@Slf4j
@RequiredArgsConstructor
public class FileController extends BaseController {

    private final SparkProperties sparkProperties;

    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传文件")
    public ApiResponse upload(@RequestParam MultipartFile file) {
        //获取文件名称
        String fileName = file.getOriginalFilename();
        String fileType = StringUtils.substringAfterLast(fileName, ".");
        String fileCode = UUID.randomUUID().toString().replace("-", "");
        String fileUploadName = fileCode + "." + fileType;
        //拼接文件上传路径
        try {
            QiNiuProperties qiNiuProperties = sparkProperties.getQiniu();
            String url  = QiNiuUtils.upload(file,fileUploadName,qiNiuProperties.getAccessKey(),qiNiuProperties.getSecretKey(),qiNiuProperties.getBucketName());
            return success("上传成功",qiNiuProperties.getDomain()+url);
        } catch (Exception e) {
            log.error("上传文件失败",e);
            return fail("上传失败");
        }
    }
}
