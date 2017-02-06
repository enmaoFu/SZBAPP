package com.bjsz.app.entity.returndata.archives;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 既往史返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-06
 */
public class MedicalhistoryData extends BaseData {

    @Expose
    private List<MedicalhistoryExpandData> data;//data数据

    public List<MedicalhistoryExpandData> getData() {
        return data;
    }

    public void setData(List<MedicalhistoryExpandData> data) {
        this.data = data;
    }
}
