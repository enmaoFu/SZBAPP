package com.bjsz.app.entity.returndata.data;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

/**
 * 获取首页健康数据返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-13
 */
public class HealthyData extends BaseData{

    @Expose
    private HealthyExpandData data;//data数据

    public HealthyExpandData getData() {
        return data;
    }

    public void setData(HealthyExpandData data) {
        this.data = data;
    }
}
