package com.spark.platform.flowable.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.flowable.api.dto
 * @ClassName: PageDTO
 * @Author: wangdingfeng
 * @Description: 分配DTO
 * @Date: 2020/10/24 14:08
 * @Version: 1.0
 */
@Data
@ApiModel(value = "PageDTO",description = "分页DTO")
public class PageDTO<T> {

    @ApiModelProperty(value = "数据集")
    private List<T> records;

    @ApiModelProperty(value = "总页数")
    private Long total;

    @ApiModelProperty(value = "一页条数")
    private Long size;

    @ApiModelProperty(value = "当前页数")
    private Long current;

    @ApiModelProperty(value = "排序")
    private String orderBy;

    public PageDTO(){
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
    }

    public PageDTO(long current, long size) {
        this(current, size, 0L);
    }

    public PageDTO(long current, long size, long total) {
        this.current = current;
        this.size = size;
        this.total = total;
    }


    /**
     * 获取首页标码
     *
     * @return
     */
    public int getFirstResult() {
        return (int) ((this.getCurrent() - 1) * this.getSize());
    }

    /**
     * 获取结尾标码
     *
     * @return
     */
    public int getMaxResults() {
        return (int) (this.getCurrent() * this.getSize());
    }

}
