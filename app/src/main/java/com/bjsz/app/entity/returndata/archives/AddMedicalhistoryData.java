package com.bjsz.app.entity.returndata.archives;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

/**
 * 添加既往史返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-07
 */
public class AddMedicalhistoryData extends BaseData{

    @Expose
    private AddMedicalhistoryExpandData data;//data数据

    public AddMedicalhistoryExpandData getData() {
        return data;
    }

    public void setData(AddMedicalhistoryExpandData data) {
        this.data = data;
    }
}
