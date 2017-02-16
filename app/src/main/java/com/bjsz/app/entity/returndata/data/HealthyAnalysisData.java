package com.bjsz.app.entity.returndata.data;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

/**
 * 获取检测报告返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-16
 */
public class HealthyAnalysisData extends BaseData{

    @Expose
    private HealthyAnalysisExpandData data;//data数据

    public HealthyAnalysisExpandData getData() {
        return data;
    }

    public void setData(HealthyAnalysisExpandData data) {
        this.data = data;
    }
}
