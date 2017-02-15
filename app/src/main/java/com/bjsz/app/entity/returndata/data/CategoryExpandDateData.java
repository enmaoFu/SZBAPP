package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 获取检测报告返回JSON实体类 一级日期
 * @author enmaoFu
 * @date 2017-02-14
 */
public class CategoryExpandDateData {

    @Expose
    private String day;

    @Expose
    private List<CategoryExpandDateTimeData> datenumber;//二级目录

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<CategoryExpandDateTimeData> getDatenumber() {
        return datenumber;
    }

    public void setDatenumber(List<CategoryExpandDateTimeData> datenumber) {
        this.datenumber = datenumber;
    }
}
