package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

/**
 * 获取首页健康数据返回JSON实体类 尿酸
 * @author enmaoFu
 * @date 2017-02-13
 */
public class HealthyExpandNsData {

    @Expose
    private String ua;

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }
}
