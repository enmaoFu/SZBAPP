package com.bjsz.app.entity.returndata.data;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

/**
 * 获取检测报告返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-14
 */
public class CategoryData extends BaseData{

    @Expose
    private CategoryExpandData data;//data数据

    public CategoryExpandData getData() {
        return data;
    }

    public void setData(CategoryExpandData data) {
        this.data = data;
    }
}
