package com.bjsz.app.entity.returndata.archives;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

/**
 * 基本信息返回JSON实体类
 * @author enmaoFu
 * @date 2017-01-21
 */
public class EssentialInformationData extends BaseData{

    @Expose
    private EssentialInformationExpandDate data;//data数据

    public EssentialInformationExpandDate getData() {
        return data;
    }

    public void setData(EssentialInformationExpandDate data) {
        this.data = data;
    }
}
