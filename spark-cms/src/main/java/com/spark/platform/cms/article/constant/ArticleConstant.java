package com.spark.platform.cms.article.constant;

/**
 * @author: wangdingfeng
 * @Date: 2020/5/2 14:48
 * @Description: 文章常量
 */
public class ArticleConstant {
    /**
     * 文章状态 0 暂存 1 退回修改 2 组长审核 3 主编审核 4 审核通过 5 审核不通过
     */
    public static final String STATUS_SAVE = "0";
    public static final String STATUS_BACK_EDIT = "1";
    public static final String STATUS_APPROVAL_GROUP = "2";
    public static final String STATUS_APPROVAL_EDITOR = "3";
    public static final String STATUS_PASS = "4";
    public static final String STATUS_FAIL = "5";
    /**
     * 业务key
     */
    public static final String PROCESS_BUSINESS_TYPE = "PROCESS_ARTICLE";
    /**
     * 流程key
     */
    public static final String PROCESS_KEY = "audit-article";
    /**
     * 互斥网关后缀
     */
    public static final String SUBMIT_SUFFIX = "_SUBMIT_VALUE";
    /**
     * 前缀
     */
    public static final String MULTIINSTANCE_RESULT_PREFIX = "multiInstance_result_";

    /**
     * 流程节点 组长审核
     */
    public static final String PROCESS_NODE_GROUP_LEADER_APPROVE = "group_leader_approve";
    /**
     * 退回修改
     */
    public static final String PROCESS_NODE_SUBMIT_APPROVAL = "submit_approval";
    /**
     * 主编1审核
     */
    public static final String PROCESS_NODE_EDITOR1_APPROVAL = "editor1_approval";
    /**
     * 主编2审核
     */
    public static final String PROCESS_NODE_EDITOR2_APPROVAL = "editor2_approval";
    /**
     * 系统判断
     */
    public static final String PROCESS_NODE_SYSTEM_JUDGE = "system_judge";


}
