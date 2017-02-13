package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

/**
 * 获取首页健康数据返回JSON实体类 心脉
 * @author enmaoFu
 * @date 2017-02-13
 */
public class HealthyExpandBmiData {

    @Expose
    private String height;

    @Expose
    private String weight;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
