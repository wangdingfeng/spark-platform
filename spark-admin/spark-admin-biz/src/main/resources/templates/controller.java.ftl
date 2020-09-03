package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
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
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} api访问层
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
@Api(tags = "${table.comment!}")
@AllArgsConstructor
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

  private final ${table.serviceName} ${table.serviceName ? uncap_first};

  @GetMapping("/page")
  @ApiOperation(value = "${table.comment!}列表")
  public ApiResponse page(${entity} ${entity ? uncap_first}, Page page){
   return success(${table.serviceName ? uncap_first}.page(page,Wrappers.query(${entity ? uncap_first})));
  }

  @PostMapping
  @ApiOperation(value = "保存${table.comment!}信息")
  public ApiResponse save(@RequestBody ${entity} ${entity ? uncap_first}){
   return success(${table.serviceName ? uncap_first}.save(${entity ? uncap_first}));
  }

  @PutMapping
  @ApiOperation(value = "更新${table.comment!}信息")
  public ApiResponse update(@RequestBody ${entity} ${entity ? uncap_first}){
   return success(${table.serviceName ? uncap_first}.updateById(${entity ? uncap_first}));
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "删除${table.comment!}")
  public ApiResponse delete(@PathVariable Long id){
   return success(${table.serviceName ? uncap_first}.removeById(id));
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "根据id获取${table.comment!}信息")
  public ApiResponse getById(@PathVariable Long id) {
   return success(${table.serviceName ? uncap_first}.getById(id));
  }

}
</#if>
