package com.spark.platform.flowable.biz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.biz.controller
 * @ClassName: IndexController
 * @Author: wangdingfeng
 * @Description: 跳转界面管理controller
 * @Date: 2020/5/27 10:38
 * @Version: 1.0
 */
@Controller
public class ViewController {

    /**
     * 设计流程图首页
     * @return
     */
    @GetMapping("")
    public String modelIndex(){
        return "redirect:/flowable/index.html";
    }

    /**
     * 流程图
     * @param id 流程定义ID
     * @param model
     * @return
     */
    @GetMapping("/flowable/process/displayModel/{id}")
    public String processDisplay(@PathVariable("id") String id, Model model){
        model.addAttribute("id",id);
        return "process/displaymodel";
    }

    /**
     * 流程图跟踪
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/flowable/instance/displayModel/{id}")
    public String instanceDisplay(@PathVariable("id") String id, Model model){
        model.addAttribute("id",id);
        return "instance/displaymodel";
    }
}
