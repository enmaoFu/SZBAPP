package com.bjsz.app.entity.returndata.archives;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 家族史返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-06
 */
public class FamilyhistoryData extends BaseData{

    @Expose
    private List<FamilyhistoryExpandData> data;//data数据

    public List<FamilyhistoryExpandData> getData() {
        return data;
    }

    public void setData(List<FamilyhistoryExpandData> data) {
        this.data = data;
    }
}
