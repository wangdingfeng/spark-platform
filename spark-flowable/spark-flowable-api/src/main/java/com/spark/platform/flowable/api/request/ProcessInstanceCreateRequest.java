package com.spark.platform.flowable.api.request;

import com.spark.platform.flowable.api.enums.VariablesEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.api.request
 * @ClassName: ProcessInstanceCreateRequest
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/6/2 11:39
 * @Version: 1.0
 */
@Data
@ApiModel(value = "ProcessInstanceCreateRequest", description = "流程发起")
public class ProcessInstanceCreateRequest {
    @ApiModelProperty(value = "流程定义ID")
    private String processDefinitionId;
    @ApiModelProperty(value = "流程KEY")
    private String processDefinitionKey;
    @ApiModelProperty(value = "业务ID")
    private String businessKey;
    @ApiModelProperty(value = "租户ID")
    private String tenantId;
    @ApiModelProperty(value = "业务类型")
    private String businessType;
    @ApiModelProperty(value = "业务名称")
    private String businessName;
    @ApiModelProperty(value = "业务编码")
    private String businessCode;
    @ApiModelProperty(value = "流程参数")
    private Map<String, Object> variables = new HashMap<>();

    public ProcessInstanceCreateRequest() {

    }

    public ProcessInstanceCreateRequest(String processDefinitionKey, String businessKey, String businessType, String businessName, String businessCode, Map<String, Object> var) {
        setProcessDefinitionKey(processDefinitionKey);
        setBusinessKey(businessKey);
        setBusinessType(businessType);
        setBusinessName(businessName);
        setBusinessCode(businessCode);
        variables.putAll(var);
        variables.put(VariablesEnum.businessType.toString(), businessType);
        variables.put(VariablesEnum.businessName.toString(), businessName);
        variables.put(VariablesEnum.businessCode.toString(), StringUtils.isNotBlank(businessCode) ? businessCode : businessKey);
    }
}
