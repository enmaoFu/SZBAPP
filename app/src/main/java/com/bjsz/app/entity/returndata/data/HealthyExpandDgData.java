package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

/**
 * 获取首页健康数据返回JSON实体类 胆固醇
 * @author enmaoFu
 * @date 2017-02-13
 */
public class HealthyExpandDgData {

    @Expose
    private String chol;

    public String getChol() {
        return chol;
    }

    public void setChol(String chol) {
        this.chol = chol;
    }
}
