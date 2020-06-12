package com.spark.platform.common.base.constants;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.common.base.constants
 * @ClassName: ScheduleConstants
 * @Author: wangdingfeng
 * @Description: 定时任务常量
 * @Date: 2020/6/9 15:32
 * @Version: 1.0
 */
public interface ScheduleConstants {
    String TASK_CLASS_NAME = "TASK_CLASS_NAME";

    /**
     * 执行目标key
     */
    String TASK_PROPERTIES = "TASK_PROPERTIES";
    /**
     * 调用类型 0 bean类型 1 rest类型 2 消息队列
     */
    String TYPE_BEAN = "0";
    String TYPE_REST = "1";
    String TYPE_RABBITMQ = "2";
    /**
     * 默认
     */
    String MISFIRE_DEFAULT = "0";

    /**
     * 立即触发执行
     */
    String MISFIRE_IGNORE_MISFIRES = "1";

    /**
     * 触发一次执行
     */
    String MISFIRE_FIRE_AND_PROCEED = "2";

    /**
     * 不触发立即执行
     */
    String MISFIRE_DO_NOTHING = "3";

    enum Status {

        PAUSE("0","暂停任务"),

        NORMAL("1","开启任务");

        private String value;

        private String msg;

        Status(String value,String msg) {
            this.value = value;
            this.msg = msg;
        }

        public String getValue() {
            return value;
        }

        public String getMsg() {
            return msg;
        }

        public static Status getEnum(String value) {
            for (Status ele : Status.values()) {
                if (ele.getValue().equals(value)) {
                    return ele;
                }
            }
            return null;
        }
    }
}

