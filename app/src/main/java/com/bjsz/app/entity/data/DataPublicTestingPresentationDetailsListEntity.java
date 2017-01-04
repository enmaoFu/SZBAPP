package com.bjsz.app.entity.data;

/**
 * 健康数据通用检测报告详情Listview实体类
 * @author enmaoFu
 * @date 2017-01-04
 */
public class DataPublicTestingPresentationDetailsListEntity {

    private String data_public_list_s;

    private String data_public_list_number;

    private String data_public_list_f;

    public DataPublicTestingPresentationDetailsListEntity(String data_public_list_s, String data_public_list_number, String data_public_list_f) {
        this.data_public_list_s = data_public_list_s;
        this.data_public_list_number = data_public_list_number;
        this.data_public_list_f = data_public_list_f;
    }

    public String getData_public_list_s() {
        return data_public_list_s;
    }

    public void setData_public_list_s(String data_public_list_s) {
        this.data_public_list_s = data_public_list_s;
    }

    public String getData_public_list_number() {
        return data_public_list_number;
    }

    public void setData_public_list_number(String data_public_list_number) {
        this.data_public_list_number = data_public_list_number;
    }

    public String getData_public_list_f() {
        return data_public_list_f;
    }

    public void setData_public_list_f(String data_public_list_f) {
        this.data_public_list_f = data_public_list_f;
    }
}
