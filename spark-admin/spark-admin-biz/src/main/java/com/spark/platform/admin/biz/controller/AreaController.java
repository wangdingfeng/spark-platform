package com.spark.platform.admin.biz.controller;


import com.spark.platform.admin.api.entity.area.Area;
import com.spark.platform.admin.biz.service.area.AreaService;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spark.platform.common.base.support.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spark.platform.common.base.support.BaseController;

/**
 * <p>
 * 行政区划 api访问层
 * </p>
 *
 * @author wangdingfeng
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/area")
@Api(tags = "行政区划")
@AllArgsConstructor
public class AreaController extends BaseController {

      private final AreaService areaService;

      @GetMapping("/list")
      @ApiOperation(value = "行政区划列表")
      public ApiResponse list(Area area){
        return success(areaService.list(area));
      }

      @PostMapping
      @ApiOperation(value = "保存行政区划信息")
      public ApiResponse save(@RequestBody Area area){
        return success(areaService.save(area));
      }

      @PutMapping
      @ApiOperation(value = "更新行政区划信息")
      public ApiResponse update(@RequestBody Area area){
        return success(areaService.updateById(area));
      }

      @DeleteMapping("/{id}")
      @ApiOperation(value = "删除行政区划")
      public ApiResponse delete(@PathVariable Long id){
        return success(areaService.removeById(id));
      }

      @GetMapping("/{id}")
      @ApiOperation(value = "根据id获取行政区划信息")
      public ApiResponse getById(@PathVariable Long id) {
        return success(areaService.getById(id));
      }

}
