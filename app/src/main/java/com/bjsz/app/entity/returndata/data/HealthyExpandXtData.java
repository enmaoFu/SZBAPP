package com.bjsz.app.entity.returndata.data;

import com.google.gson.annotations.Expose;

/**
 * 获取首页健康数据返回JSON实体类 血糖
 * @author enmaoFu
 * @date 2017-02-13
 */
public class HealthyExpandXtData {

    @Expose
    private String glu;

    public String getGlu() {
        return glu;
    }

    public void setGlu(String glu) {
        this.glu = glu;
    }
}
