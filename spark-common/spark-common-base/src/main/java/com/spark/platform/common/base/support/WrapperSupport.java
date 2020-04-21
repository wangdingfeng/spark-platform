package com.spark.platform.common.base.support;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wangdingfeng
 * @Date: 2020/4/19 14:14
 * @Description: Wrapper 支持类
 */
@Slf4j
public class WrapperSupport {

    /**
     * TODO 等于参数
     *
     * @param wrapper
     * @param bean      class
     * @param propNames 属性名
     * @return
     */
    public static void putParamsEqual(QueryWrapper wrapper, Object bean, String... propNames) {
        putParamsEqual(wrapper, "", bean, propNames);
    }

    /**
     * TODO 等于参数
     *
     * @param wrapper
     * @param bean      class
     * @param aliasName 别名
     * @param propNames 属性名
     * @return
     */
    public static void putParamsEqual(QueryWrapper wrapper, String aliasName, Object bean, String... propNames) {
        for (String propName : propNames) {
            try {
                StringBuilder whereColumn = joinColumn(aliasName, propName);
                Object value = getValue(bean, propName);
                if (!nullIsObject(value)) {
                    continue;
                }
                wrapper.eq(whereColumn.toString(), value);
            } catch (Exception e) {
                log.error("属性转换出错，清查看错误日志", e);
            }
        }
    }

    /**
     * TODO in条件
     *
     * @param wrapper
     * @param bean      对象
     * @param propNames 属性名
     */
    public static void putParamsIn(QueryWrapper wrapper, Object bean, String... propNames) {
        putParamsIn(wrapper, "", bean, propNames);

    }

    /**
     * TODO in条件
     *
     * @param wrapper
     * @param aliasName 别名
     * @param bean      对象
     * @param propNames 属性名
     */
    public static void putParamsIn(QueryWrapper wrapper, String aliasName, Object bean, String... propNames) {
        for (String propName : propNames) {
            try {
                StringBuilder whereColumn = joinColumn(aliasName, propName);
                Object value = getValue(bean, propName);
                if (!nullIsObject(value)) {
                    continue;
                }
                wrapper.in(whereColumn.toString(), value);
            } catch (Exception e) {
                log.error("属性转换出错，清查看错误日志", e);
            }
        }
    }

    /**
     * TODO like条件
     *
     * @param wrapper
     * @param bean      对象
     * @param propNames 属性名
     */
    public static void putParamsLike(QueryWrapper wrapper, Object bean, String... propNames) {
        putParamsLike(wrapper, "", bean, propNames);
    }

    /**
     * TODO like条件
     *
     * @param wrapper
     * @param aliasName 别名
     * @param bean      对象
     * @param propNames 属性名
     */
    public static void putParamsLike(QueryWrapper wrapper, String aliasName, Object bean, String... propNames) {
        for (String propName : propNames) {
            try {
                StringBuilder whereColumn = joinColumn(aliasName, propName);
                Object value = getValue(bean, propName);
                if (!nullIsObject(value)) {
                    continue;
                }
                wrapper.like(whereColumn.toString(), value);
            } catch (Exception e) {
                log.error("属性转换出错，清查看错误日志", e);
            }
        }
    }


    /**
     * TODO 时间参数
     *
     * @param wrapper
     * @param value   值
     * @param split   切割字段
     * @param column  sql查询列
     */
    public static void putParamsDateBetween(QueryWrapper wrapper, String value, String split, String column) {
        if (StringUtils.isNotBlank(value)) {
            String[] dataArg = value.split(split);
            try {
                Date beginOfDay = beginOfDay(DateUtils.parseDate(dataArg[0], "yyyy-MM-dd HH:mm:ss"));
                Date endOfDay = endOfDay(DateUtils.parseDate(dataArg[1], "yyyy-MM-dd HH:mm:ss"));
                wrapper.between(column, beginOfDay, endOfDay);
            } catch (ParseException e) {
                log.error("转换时间出错", e);
            }
        }
    }

    /**
     * 拼接参数
     *
     * @param aliasName 别名
     * @param propName  列名
     * @return
     */
    private static StringBuilder joinColumn(String aliasName, String propName) {
        StringBuilder whereColumn = new StringBuilder();
        String column = camel4underline(propName);
        if (StringUtils.isNotBlank(aliasName)) whereColumn.append(aliasName).append(".");
        whereColumn.append(column);
        return whereColumn;
    }

    /**
     * TODO 大写转转小写下划线  xxAxx to xx_axx
     *
     * @param param
     * @return
     */
    public static String camel4underline(String param) {
        Pattern p = Pattern.compile("[A-Z]");
        if (param == null || param.equals("")) {
            return "";
        }
        StringBuilder builder = new StringBuilder(param);
        Matcher mc = p.matcher(param);
        int i = 0;
        while (mc.find()) {
            builder.replace(mc.start() + i, mc.end() + i, "_" + mc.group().toLowerCase());
            i++;
        }
        if ('_' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    /**
     * 判断值是否是空
     *
     * @param value
     * @return
     */
    private static boolean nullIsObject(Object value) {
        boolean flag = false;
        //判断字段类型
        if (null != value) {
            //字符串还需判断是否为空字符串
            if (value instanceof String) {
                if (((String) value).trim().length() > 0) {
                    flag = true;
                }
            } else {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 反射获取值
     *
     * @param instance
     * @param fieldName
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static Object getValue(Object instance, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Field field = getField(instance.getClass(), fieldName);
        // 参数值为true，禁用访问控制检查
        field.setAccessible(true);
        return field.get(instance);
    }

    /**
     * 反射获取
     *
     * @param thisClass
     * @param fieldName
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getField(Class thisClass, String fieldName) throws NoSuchFieldException {
        if (fieldName == null) {
            throw new NoSuchFieldException("Error field !");
        }
        // 绑定当前类、及父类所有属性合并
        List<Field> fieldList = new ArrayList<Field>();
        // 获取当前实体类的所有属性（不包含继承的类的属性），返回Field数组
        Field[] fieldChi = thisClass.getDeclaredFields();
        fieldList.addAll(Arrays.asList(fieldChi));
        // 获取该实体类父类所有属性
        Field[] fieldParent = thisClass.getSuperclass().getDeclaredFields();
        fieldList.addAll(Arrays.asList(fieldParent));
        // 返回相应字段属性值
        for (Field item : fieldList) {
            if (item.getName().equals(fieldName)) {
                return item; //子类存在则直接返回
            }
        }
        return null;
    }

    /**
     * 获取每天的开始时间 00:00:00:00
     *
     * @param date
     * @return
     */
    public static Date beginOfDay(Date date) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(date);
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.set(Calendar.MINUTE, 0);
        dateStart.set(Calendar.SECOND, 0);
        return dateStart.getTime();
    }

    /**
     * 获取每天的开始时间 23:59:59:999
     *
     * @param date
     * @return
     */
    public static Date endOfDay(Date date) {
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        return dateEnd.getTime();
    }
}
