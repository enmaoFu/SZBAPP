package com.bjsz.app.entity.returndata.archives;

import com.google.gson.annotations.Expose;

/**
 * 家族史返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-06
 */
public class FamilyhistoryExpandData {

    @Expose
    private String illness;//疾病名字

    @Expose
    private String hbtime;//开始时间

    @Expose
    private String zytime;//结束时间

    @Expose
    private String zy;//是否治愈

    @Expose
    private String ship;//患病家属名字

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getHbtime() {
        return hbtime;
    }

    public void setHbtime(String hbtime) {
        this.hbtime = hbtime;
    }

    public String getZytime() {
        return zytime;
    }

    public void setZytime(String zytime) {
        this.zytime = zytime;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getShip() {
        return ship;
    }

    public void setShip(String ship) {
        this.ship = ship;
    }
}
