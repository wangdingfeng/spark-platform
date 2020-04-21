package com.spark.platform.flowable.biz.controller;

import com.spark.platform.common.base.support.BaseController;
import com.spark.platform.flowable.biz.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/6 14:37
 * @Description:
 */
@RestController
@RequestMapping("runtime/image")
@Api(value = "Image", tags = {"流程跟踪"})
@Slf4j
public class ImageController extends BaseController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/byte/", produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiOperation(value = "流程跟踪图", notes = "图片生成，接口返回")
    @ApiImplicitParams({@ApiImplicitParam(name = "procInstId", value = "流程实例ID", required = true, dataType = "String")})
    public byte[] generateImage(String procInstId) throws Exception {
        return imageService.generateImageByProcInstId(procInstId);
    }

    @GetMapping(value = "/{processId}")
    @ApiOperation(value = "流程跟踪图", notes = "生成到本地文件夹下，前端再读取", produces = "application/json")
    public void viewProcessImg(@PathVariable String processId, HttpServletResponse response) throws IOException {
        OutputStream os = null;
        try {
            byte[] processImage = imageService.generateImageByProcInstId(processId);
            response.reset();
            response.setContentType("image/png");
            //禁止图片缓存
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(processImage);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            log.error("流程跟踪图生成失败： {}", ExceptionUtils.getStackTrace(e));
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
