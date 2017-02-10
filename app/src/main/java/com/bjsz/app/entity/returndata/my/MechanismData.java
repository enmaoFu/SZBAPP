package com.bjsz.app.entity.returndata.my;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 获取我的机构返回JSON实体类
 * @author enmaoFu
 * @dade 2017-02-10
 */
public class MechanismData extends BaseData{

    @Expose
    private List<MechanismExpandData> data;//data数据

    public List<MechanismExpandData> getData() {
        return data;
    }

    public void setData(List<MechanismExpandData> data) {
        this.data = data;
    }
}
