package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

/**
 * 获取首页健康数据返回JSON实体类 血压
 * @author enmaoFu
 * @date 2017-02-13
 */
public class HealthyExpandXyData {

    @Expose
    private String sys;

    @Expose
    private String dia;

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
