package com.spark.platform.adminapi.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.adminapi.vo
 * @ClassName: VueTree
 * @Author: wangdingfeng
 * @Description: vue 树节点数据
 * @Date: 2020/3/20 15:19
 * @Version: 1.0
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VueTree {

    private Long id;

    private String label;

    private Long pid;

    private List<VueTree> children;
}
