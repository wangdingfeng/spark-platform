package com.spark.platform.adminapi.vo;


import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/5/31 10:45
 * @Description:
 */
public class IndexDataVo {

    /**
     * 今日登陆ip数
     */
    private int todayIPNum;
    /**
     * 访问量
     */
    private int visits;
    /**
     * 流量数据
     */
    private VisitsVo visitsVo;


    public static class VisitsVo {
        /**
         * 时间段
         */
        private List<String> dates;
        /**
         * 我的数据
         */
        private List<Integer> myData;
        /**
         * 全部数据
         */
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
