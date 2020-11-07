package com.spark.platform.admin.api.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/5/31 10:45
 * @Description: 主页信息VO
 */
@ApiModel(value = "IndexDataVo",description = "主页信息VO")
public class IndexDataVo {

    @ApiModelProperty(value = "今日登陆ip数")
    private int todayIPNum;

    @ApiModelProperty(value = "访问量")
    private int visits;

    @ApiModelProperty(value = "流量数据")
    private VisitsVo visitsVo;


    public static class VisitsVo {

        @ApiModelProperty(value = "时间段")
        private List<String> dates;

        @ApiModelProperty(value = "我的数据")
        private List<Integer> myData;

        @ApiModelProperty(value = "全部数据")
        private List<Integer> allData;

        public List<String> getDates() {
            return dates;
        }

        public void setDates(List<String> dates) {
            this.dates = dates;
        }

        public List<Integer> getMyData() {
            return myData;
        }

        public void setMyData(List<Integer> mydData) {
            this.myData = mydData;
        }

        public List<Integer> getAllData() {
            return allData;
        }

        public void setAllData(List<Integer> allData) {
            this.allData = allData;
        }
    }

    public int getTodayIPNum() {
        return todayIPNum;
    }

    public void setTodayIPNum(int todayIPNum) {
        this.todayIPNum = todayIPNum;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public VisitsVo getVisitsVo() {
        return visitsVo;
    }

    public void setVisitsVo(VisitsVo visitsVo) {
        this.visitsVo = visitsVo;
    }
}
