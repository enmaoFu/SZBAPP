package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

/**
 * 获取首页健康数据返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-13
 */
public class HealthyExpandData {

    @Expose
    private HealthyExpandBmiData bmi;//心脉

    @Expose
    private HealthyExpandXyData xy;//血压

    @Expose
    private HealthyExpandXtData xt;//血糖

    @Expose
    private HealthyExpandYxData yx;//血氧

    @Expose
    private HealthyExpandTwData tw;//体温

    @Expose
    private HealthyExpandNsData ns;//尿酸

    @Expose
    private HealthyExpandDgData dg;//胆固醇

    @Expose
    private String ncg;//尿常规

    @Expose
    private String xd;//心电

    public HealthyExpandBmiData getBmi() {
        return bmi;
    }

    public void setBmi(HealthyExpandBmiData bmi) {
        this.bmi = bmi;
    }

    public HealthyExpandXyData getXy() {
        return xy;
    }

    public void setXy(HealthyExpandXyData xy) {
        this.xy = xy;
    }

    public HealthyExpandXtData getXt() {
        return xt;
    }

    public void setXt(HealthyExpandXtData xt) {
        this.xt = xt;
    }

    public HealthyExpandYxData getYx() {
        return yx;
    }

    public void setYx(HealthyExpandYxData yx) {
        this.yx = yx;
    }

    public HealthyExpandTwData getTw() {
        return tw;
    }

    public void setTw(HealthyExpandTwData tw) {
        this.tw = tw;
    }

    public HealthyExpandNsData getNs() {
        return ns;
    }

    public void setNs(HealthyExpandNsData ns) {
        this.ns = ns;
    }

    public HealthyExpandDgData getDg() {
        return dg;
    }

    public void setDg(HealthyExpandDgData dg) {
        this.dg = dg;
    }

    public String getNcg() {
        return ncg;
    }

    public void setNcg(String ncg) {
        this.ncg = ncg;
    }

    public String getXd() {
        return xd;
    }

    public void setXd(String xd) {
        this.xd = xd;
    }
}
