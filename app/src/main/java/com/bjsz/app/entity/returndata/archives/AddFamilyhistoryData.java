package com.bjsz.app.entity.returndata.archives;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

/**
 * 添加家族史返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-09
 */
public class AddFamilyhistoryData extends BaseData{

    @Expose
    private AddFamilyhistoryExpandData data;//data数据

    public AddFamilyhistoryExpandData getData() {
        return data;
    }

    public void setData(AddFamilyhistoryExpandData data) {
        this.data = data;
    }
}
