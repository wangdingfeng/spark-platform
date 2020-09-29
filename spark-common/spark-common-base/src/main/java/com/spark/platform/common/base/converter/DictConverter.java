package com.spark.platform.common.base.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.spark.platform.common.base.annotation.DictProperty;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.utils.RedisUtils;
import com.spark.platform.common.base.utils.SpringContextHolder;
import com.spark.platform.common.base.vo.DictVo;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.converter
 * @ClassName: DictConverter
 * @Author: wangdingfeng
 * @Description: 阿里excel 字典转换器
 * @Date: 2020/9/28 14:32
 * @Version: 1.0
 */
public class DictConverter implements Converter<Object> {

    private final RedisUtils redisUtils = SpringContextHolder.getBean(RedisUtils.class);

    @Override
    public Class supportJavaTypeKey() {
        return null;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public Object convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    @Override
    public CellData convertToExcelData(Object o, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new CellData(getDictLabel(o,excelContentProperty));
    }

    /**
     * 字典翻译
     * @param o
     * @param excelContentProperty
     */
    private String getDictLabel(Object o,ExcelContentProperty excelContentProperty){
        if(null == o || "".equals(o.toString())){
            return "";
        }
        Field field =  excelContentProperty.getField();
        //获取字典注解
        DictProperty dictProperty = field.getAnnotation(DictProperty.class);
        if(null != dictProperty){
            String type = dictProperty.type();
            String json = redisUtils.get(GlobalsConstants.REDIS_DICT_CACHE+"::"+GlobalsConstants.DICT_KEY_All_PREFIX);
            //获取字典
            Map<String, JSONArray> map = JSON.parseObject(json,Map.class);
            JSONArray jsonArray = map.get(type);
            if(CollectionUtils.isEmpty(jsonArray)){
                return o.toString();
            }
            for(Object o1 : jsonArray){
                DictVo dictVo = (DictVo)o1;
                if(o.toString().equals(dictVo.getValue())){
                    return dictVo.getLabel();
                }
            }
        }
        return "";
    }
}
