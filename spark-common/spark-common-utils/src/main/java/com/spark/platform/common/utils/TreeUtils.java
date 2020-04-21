package com.spark.platform.common.utils;


import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.utils
 * @ClassName: TreeUtils
 * @Author: wangdingfeng
 * @Description:
 * @Date: 2020/3/16 16:56
 * @Version: 1.0
 */
public class TreeUtils {
    /**
     * 集合转树结构
     *
     * @param collection 目标集合
     * @param clazz      集合元素类型
     * @return 转换后的树形结构
     */
    public static <T> Collection<T> toTree(@NotNull Collection<T> collection, @NotNull Class<T> clazz) {
        return toTree(collection, null, null, null, clazz);
    }

    /**
     * 集合转树结构
     *
     * @param collection 目标集合
     * @param id         节点编号字段名称
     * @param parent     父节点编号字段名称
     * @param children   子节点集合属性名称
     * @param clazz      集合元素类型
     * @return           转换后的树形结构
     */
    public static <T> Collection<T> toTree(@NotNull Collection<T> collection, String id, String parent, String children, @NotNull Class<T> clazz) {
        try {
            if (collection == null || collection.isEmpty()) return null;// 如果目标集合为空,直接返回一个空树
            if (StringUtils.isEmpty(id)) id = "id";                     // 如果被依赖字段名称为空则默认为id
            if (StringUtils.isEmpty(parent)) parent = "pid";         // 如果依赖字段为空则默认为parent
            if (StringUtils.isEmpty(children)) children = "children";   // 如果子节点集合属性名称为空则默认为children

            // 初始化根节点集合, 支持 Set 和 List
            Collection<T> roots;
            if (collection.getClass().isAssignableFrom(Set.class)) {
                roots = new HashSet<>();
            } else {
                roots = new ArrayList<>();
            }

            // 获取 id 字段, 从当前对象或其父类
            Field idField;
            try {
                idField = clazz.getDeclaredField(id);
            } catch (NoSuchFieldException e1) {
                idField = clazz.getSuperclass().getDeclaredField(id);
            }

            // 获取 parentId 字段, 从当前对象或其父类
            Field parentField;
            try {
                parentField = clazz.getDeclaredField(parent);
            } catch (NoSuchFieldException e1) {
                parentField = clazz.getSuperclass().getDeclaredField(parent);
            }

            // 获取 children 字段, 从当前对象或其父类
            Field childrenField;
            try {
                childrenField = clazz.getDeclaredField(children);
            } catch (NoSuchFieldException e1) {
                childrenField = clazz.getSuperclass().getDeclaredField(children);
            }

            // 设置为可访问
            idField.setAccessible(true);
            parentField.setAccessible(true);
            childrenField.setAccessible(true);

            // 找出所有的根节点
            for (T c : collection) {
                Object parentId = parentField.get(c);
                if (isRootNode(parentId)) {
                    roots.add(c);
                }
            }

            // 从目标集合移除所有根节点
            collection.removeAll(roots);

            // 遍历根节点, 依次添加子节点
            for (T root : roots) {
                addChild(root, collection, idField, parentField, childrenField);
            }

            // 关闭可访问
            idField.setAccessible(false);
            parentField.setAccessible(false);
            childrenField.setAccessible(false);

            return roots;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 为目标节点添加孩子节点
     *
     * @param node             目标节点
     * @param collection    目标集合
     * @param idField       ID 字段
     * @param parentField   父节点字段
     * @param childrenField 字节点字段
     */
    private static <T> void addChild(@NotNull T node, @NotNull Collection<T> collection, @NotNull Field idField, @NotNull Field parentField, @NotNull Field childrenField) throws IllegalAccessException {
        Object id = idField.get(node);
        Collection<T> children = (Collection<T>) childrenField.get(node);
        // 如果子节点的集合为 null, 初始化孩子集合
        if (children == null) {
            if (collection.getClass().isAssignableFrom(Set.class)) {
                children = new HashSet<>();
            } else children = new ArrayList<>();
        }

        for (T t : collection) {
            Object o = parentField.get(t);
            if (id.equals(o)) {
                // 将当前节点添加到目标节点的孩子节点
                children.add(t);
                // 重设目标节点的孩子节点集合,这里必须重设,因为如果目标节点的孩子节点是null的话,这样是没有地址的,就会造成数据丢失,所以必须重设,如果目标节点所在类的孩子节点初始化为一个空集合,而不是null,则可以不需要这一步,因为java一切皆指针
                childrenField.set(node, children);
                // 递归添加孩子节点
                addChild(t, collection, idField, parentField, childrenField);
            }
        }
    }

    /**
     * 判断是否是根节点, 判断方式为: 父节点编号为空或为 0, 则认为是根节点. 此处的判断应根据自己的业务数据而定.
     * @param parentId      父节点编号
     * @return              是否是根节点
     */
    private static boolean isRootNode(Object parentId) {
        boolean flag = false;
        if (parentId == null) {
            flag = true;
        } else if (parentId instanceof String && (StringUtils.isEmpty(parentId) || parentId.equals("0"))) {
            flag = true;
        } else if (parentId instanceof Integer && Integer.valueOf(0).equals(parentId)) {
            flag = true;
        }else if (parentId instanceof Long && Long.valueOf(0).equals(parentId)) {
            flag = true;
        }
        return flag;
    }
}
