package com.bjsz.app.entity.data;

/**
 * 健康数据通用检测报告详情GridView实体类
 * @author enmaoFu
 * @date 2017-01-04
 */
public class DataPublicTestingPresentationDetailsGridEntity {

    private String is;

    private String re_number;

    private String re_text;

    public DataPublicTestingPresentationDetailsGridEntity(String is, String re_number, String re_text) {
        this.is = is;
        this.re_number = re_number;
        this.re_text = re_text;
    }

    public String getIs() {
        return is;
    }

    public void setIs(String is) {
        this.is = is;
    }

    public String getRe_number() {
        return re_number;
    }

    public void setRe_number(String re_number) {
        this.re_number = re_number;
    }

    public String getRe_text() {
        return re_text;
    }

    public void setRe_text(String re_text) {
        this.re_text = re_text;
    }
}
