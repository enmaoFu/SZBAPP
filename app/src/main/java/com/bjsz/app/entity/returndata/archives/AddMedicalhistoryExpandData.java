package com.bjsz.app.entity.returndata.archives;

import com.google.gson.annotations.Expose;

/**
 * 添加既往史返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-07
 */
public class AddMedicalhistoryExpandData {

    @Expose
    private String illness;//疾病名称

    @Expose
    private String cure;//是否治愈

    @Expose
    private String sicken_time;//患病时间

    @Expose
    private String cure_time;//治愈时间

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

    public String getSicken_time() {
        return sicken_time;
    }

    public void setSicken_time(String sicken_time) {
        this.sicken_time = sicken_time;
    }

    public String getCure_time() {
        return cure_time;
    }

    public void setCure_time(String cure_time) {
        this.cure_time = cure_time;
    }
}
