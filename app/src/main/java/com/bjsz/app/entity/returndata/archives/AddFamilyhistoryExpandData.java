package com.bjsz.app.entity.returndata.archives;

import com.google.gson.annotations.Expose;

/**
 * 添加家族史返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-09
 */
public class AddFamilyhistoryExpandData {

    @Expose
    private String illness;//疾病名称

    @Expose
    private String cure;//是否治愈 0：未治愈 1：已治愈

    @Expose
    private String cure_time;//治愈时间

    @Expose
    private String relation;//与患病人关系 如表姐等关系

    @Expose
    private String sicken_time;//患病时间

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getCure() {
        return cure;
    }

    public void setCure(String cure) {
        this.cure = cure;
    }

    public String getCure_time() {
        return cure_time;
    }

    public void setCure_time(String cure_time) {
        this.cure_time = cure_time;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getSicken_time() {
        return sicken_time;
    }

    public void setSicken_time(String sicken_time) {
        this.sicken_time = sicken_time;
    }
}
