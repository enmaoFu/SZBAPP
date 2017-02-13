package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

/**
 * 获取首页健康数据返回JSON实体类 血氧
 * @author enmaoFu
 * @date 2017-02-13
 */
public class HealthyExpandYxData {

    @Expose
    private String spo2;

    @Expose
    private String pr;

    public String getSpo2() {
        return spo2;
    }

    public void setSpo2(String spo2) {
        this.spo2 = spo2;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }
}
