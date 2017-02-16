package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 获取检测报告返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-16
 */
public class HealthyAnalysisExpandData {

    @Expose
    private String zc;

    @Expose
    private String yc;

    @Expose
    private int normal_rate;

    @Expose
    private List<HealthyAnalysisExpandSevenData> days;

    @Expose
    private String measure_details;

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getYc() {
        return yc;
    }

    public void setYc(String yc) {
        this.yc = yc;
    }

    public int getNormal_rate() {
        return normal_rate;
    }

    public void setNormal_rate(int normal_rate) {
        this.normal_rate = normal_rate;
    }

    public List<HealthyAnalysisExpandSevenData> getDays() {
        return days;
    }

    public void setDays(List<HealthyAnalysisExpandSevenData> days) {
        this.days = days;
    }

    public String getMeasure_details() {
        return measure_details;
    }

    public void setMeasure_details(String measure_details) {
        this.measure_details = measure_details;
    }
}
