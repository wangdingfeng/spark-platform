package com.spark.platform.cms.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.google.common.collect.Lists;
import com.spark.platform.common.base.constants.GlobalsConstants;
import com.spark.platform.common.base.datasource.annotation.DataScope;
import com.spark.platform.common.base.datasource.emuns.DataScopeTypeEnum;
import com.spark.platform.common.security.model.LoginUser;
import com.spark.platform.common.security.util.UserUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminbiz.config
 * @ClassName: DataScopeInterceptor
 * @Author: wangdingfeng
 * @Description: 拦截器 主要用于数据权限的拦截
 * @Version: 1.0
 */
@Order(90)
@Slf4j
@AllArgsConstructor
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
@Component
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {
    //后期改造成 从角色获取权限信息
    private DataSource dataSource;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        this.sqlParser(metaObject);
        // 先判断是不是SELECT操作 不是直接过滤
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (!SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        // 执行的SQL语句
        String originalSql = boundSql.getSql();
        DataScope dataScope = getDataPermission(mappedStatement);
        if (null != dataScope) {
            log.info("\n 数据权限过滤 method -> {}", mappedStatement.getId());
            originalSql = dataScopeSql(originalSql, dataScope);
            metaObject.setValue("delegate.boundSql.sql", originalSql);

        }
        return invocation.proceed();
    }

    /**
     * 生成拦截对象的代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * mybatis配置的属性
     *
     * @param properties mybatis配置的属性
     */
    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 获取注册权限信息
     *
     * @param mappedStatement
     * @return
     */
    private DataScope getDataPermission(MappedStatement mappedStatement) {
        String mappedStatementId = mappedStatement.getId();
        String mName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
        DataScope dataScope = null;
        try {
            String className = mappedStatementId.substring(0, mappedStatementId.lastIndexOf("."));
            final Class<?> clazz = Class.forName(className);
            for (Method method : clazz.getDeclaredMethods()) {
                if (mName.equals(method.getName()) && method.isAnnotationPresent(DataScope.class)) {
                    dataScope = method.getAnnotation(DataScope.class);
                    break;
                }
            }

        } catch (Exception ignore) {
        }
        return dataScope;
    }

    /**
     * 拼接权限sql
     *
     * @param sql
     * @param dataScope
     * @return
     */
    private String dataScopeSql(String sql, DataScope dataScope) throws Throwable {
        LoginUser user = UserUtils.getLoginUser();
        if (null == user) {
            return sql;
        }
        // 获取用户角色code
        List<String> roleIdList = user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith("role_"))
                .collect(Collectors.toList());
        //如果是超级管理员角色 不做任何数据权限
        if (roleIdList.contains(GlobalsConstants.ROLE_ADMIN)) {
            return sql;
        }
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        Select select;
        select = (Select) parserManager.parse(new StringReader(sql));
        PlainSelect plain = (PlainSelect) select.getSelectBody();
        Table fromItem = (Table) plain.getFromItem();
        //有别名用别名，无别名用表名，防止字段冲突报错
        String mainTableName = fromItem.getAlias() == null ? fromItem.getName() : fromItem.getAlias().getName();
        List<String> customizeList = Arrays.asList(dataScope.customize());
        //判断是否使用角色权限
        if (dataScope.isRole()) {
            //查询此角色信息
            Entity query = Db.use(dataSource)
                    .query("SELECT * FROM sys_role where role_code IN (" + CollUtil.join(roleIdList, ",") + ")")
                    .stream().min(Comparator.comparingInt(o -> o.getInt("ds_type"))).get();
            // 数据库权限范围字段
            Integer dsType = query.getInt("ds_type");
            // 查询全部
            if (DataScopeTypeEnum.ALL.getType() == dsType) {
                return sql;
            }
            //除了全部 则要获取自定义 本级及其下级 查询本级
            String dsScope = query.getStr("ds_scope");
            customizeList = Lists.newArrayList();
            customizeList.addAll(Arrays.asList(dsScope.split(",")));
        } else {
            //注解式权限
            if (dataScope.customize().length == 0) {
                customizeList = Lists.newArrayList(user.getDeptId().toString());
            }
        }
        String dataScopeSql = StringUtils.format("%s.%s in (%s)", mainTableName, dataScope.field(), CollUtil.join(customizeList, ","));
        ;
        if (plain.getWhere() == null) {
            plain.setWhere(CCJSqlParserUtil.parseCondExpression(dataScopeSql));
        } else {
            plain.setWhere(new AndExpression(plain.getWhere(), CCJSqlParserUtil.parseCondExpression(dataScopeSql)));
        }
        return select.toString();
    }

}
