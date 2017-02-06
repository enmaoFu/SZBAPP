package com.bjsz.app.entity.returndata.archives;

import com.google.gson.annotations.Expose;

/**
 * 既往史返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-06
 */
public class MedicalhistoryExpandData {

    @Expose
    private String jws;//疾病名字

    @Expose
    private String zytime;//治愈时间

    @Expose
    private String zy;//是否治愈

    public String getJws() {
        return jws;
    }

    public void setJws(String jws) {
        this.jws = jws;
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
}
