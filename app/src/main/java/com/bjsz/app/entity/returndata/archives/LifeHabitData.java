package com.bjsz.app.entity.returndata.archives;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * 生活习惯返回JSON实体类
 * @author enmaoFu
 * @date 2017-01-22
 */
public class LifeHabitData extends BaseData{

    @Expose
    private List<LifeHabitExpandData> data;//data数据

    public List<LifeHabitExpandData> getData() {
        return data;
    }

    public void setData(List<LifeHabitExpandData> data) {
        this.data = data;
    }
}
