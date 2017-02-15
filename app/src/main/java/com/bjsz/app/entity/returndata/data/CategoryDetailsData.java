package com.bjsz.app.entity.returndata.data;

import com.bjsz.app.entity.returndata.BaseData;
import com.google.gson.annotations.Expose;

/**
 * 获取检测报告详情返回JSON实体类
 * @author enmaoFu
 * @date 2017-02-15
 */
public class CategoryDetailsData extends BaseData{

    @Expose
    private CategoryDetailsExpandData data;

    public CategoryDetailsExpandData getData() {
        return data;
    }

    public void setData(CategoryDetailsExpandData data) {
        this.data = data;
    }

}
