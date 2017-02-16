package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

/**
 * 获取检测报告返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-16
 */
public class SevenTimesMoreExpandData {

    @Expose
    private String time;

    @Expose
    private String year;

    @Expose
    private String month;

    @Expose
    private String nums;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }
}
