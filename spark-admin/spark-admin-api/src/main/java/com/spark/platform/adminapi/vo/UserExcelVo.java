package com.spark.platform.adminapi.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.spark.platform.common.base.annotation.DictProperty;
import com.spark.platform.common.base.converter.DictConverter;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.vo
 * @ClassName: UserExcelVo
 * @Author: wangdingfeng
 * @Description: 导出用户信息excel
 * @Date: 2020/9/28 14:48
 * @Version: 1.0
 */
@Data
@ApiModel(value = "UserExcelVo",description = "用户信息excel")
public class UserExcelVo {

    @ExcelProperty(value = "账号",index = 0)
    private String username;

    @ExcelProperty(value = "用户姓名",index = 1)
    private String nickname;

    @ExcelProperty(value = "性别",index = 2, converter = DictConverter.class)
    @DictProperty(type = "sex")
    private Integer sex;

    @ExcelProperty(value = "电话",index = 3)
    private String phone;

    @ExcelProperty(value = "email",index = 4)
    private String email;

    @ExcelProperty(value = "部门",index = 5)
    private String deptName;

    @ExcelProperty(value = "状态",index = 6, converter = DictConverter.class)
    @DictProperty(type = "user_status")
    private Integer status;
}
