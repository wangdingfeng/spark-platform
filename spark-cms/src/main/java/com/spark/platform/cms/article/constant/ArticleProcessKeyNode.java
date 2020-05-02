package com.spark.platform.cms.article.constant;

import lombok.Getter;

/**
 * @author: wangdingfeng
 * @Date: 2020/5/2 15:16
 * @Description: 文章审核流程 下个节点 对应的key
 */
@Getter
public enum ArticleProcessKeyNode {


    group_leader_pass("group_leader_approve","组长审核成功",true,"GROUP_EDITOR"),
    group_leader_fail("group_leader_approve","组长审核失败",false,"SUBMIT_APPROVAL"),
    submit_approval_pass("submit_approval","退回提交",true,"SUBMIT_APPROVAL"),
    submit_approval_fail("submit_approval","退回修改关闭",false,"OVER"),
    system_judge_pass("system_judge","系统判断通过",true,"PASS"),
    system_judge_fail("system_judge","系统判断通过",false,"SUBMIT_APPROVAL");

    /**
     * 当前流程key
     */
    private String key;
    /**
     * 描述
     */
    private String desc;
    /**
     * 审核状态
     */
    private boolean result;
    /**
     * 下个节点
     */
    private String targetNode;


    ArticleProcessKeyNode(String key,String desc, boolean result, String targetNode){
        this.key = key;
        this.desc = desc;
        this.result = result;
        this.targetNode = targetNode;
    }
    public static ArticleProcessKeyNode getProcessNextKeyNodeByKey(String key,boolean result){
        for(ArticleProcessKeyNode pk : ArticleProcessKeyNode.values()){
            if(pk.getKey().equals(key) && pk.isResult() == result){
                return pk;
            }
        }
        return null;
    }


}
