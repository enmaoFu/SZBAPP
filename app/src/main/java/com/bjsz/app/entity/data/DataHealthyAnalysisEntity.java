package com.bjsz.app.entity.data;

/**
 * 健康数据健康分析页面时光轴实体类,以及时光轴更多页面通用
 * @author enmaoFu
 * @date 2017-01-06
 */
public class DataHealthyAnalysisEntity {

    private String data_healthy_analysis_itme_date_one;

    private String data_healthy_analysis_itme_date_to;

    private String data_healthy_analysis_itme_date_three;

    private String data_healthy_analysis_itme_text;

    public DataHealthyAnalysisEntity(String data_healthy_analysis_itme_date_one, String data_healthy_analysis_itme_date_to, String data_healthy_analysis_itme_date_three, String data_healthy_analysis_itme_text) {
        this.data_healthy_analysis_itme_date_one = data_healthy_analysis_itme_date_one;
        this.data_healthy_analysis_itme_date_to = data_healthy_analysis_itme_date_to;
        this.data_healthy_analysis_itme_date_three = data_healthy_analysis_itme_date_three;
        this.data_healthy_analysis_itme_text = data_healthy_analysis_itme_text;
    }

    public String getData_healthy_analysis_itme_date_one() {
        return data_healthy_analysis_itme_date_one;
    }

    public void setData_healthy_analysis_itme_date_one(String data_healthy_analysis_itme_date_one) {
        this.data_healthy_analysis_itme_date_one = data_healthy_analysis_itme_date_one;
    }

    public String getData_healthy_analysis_itme_date_to() {
        return data_healthy_analysis_itme_date_to;
    }

    public void setData_healthy_analysis_itme_date_to(String data_healthy_analysis_itme_date_to) {
        this.data_healthy_analysis_itme_date_to = data_healthy_analysis_itme_date_to;
    }

    public String getData_healthy_analysis_itme_date_three() {
        return data_healthy_analysis_itme_date_three;
    }

    public void setData_healthy_analysis_itme_date_three(String data_healthy_analysis_itme_date_three) {
        this.data_healthy_analysis_itme_date_three = data_healthy_analysis_itme_date_three;
    }

    public String getData_healthy_analysis_itme_text() {
        return data_healthy_analysis_itme_text;
    }

    public void setData_healthy_analysis_itme_text(String data_healthy_analysis_itme_text) {
        this.data_healthy_analysis_itme_text = data_healthy_analysis_itme_text;
    }
}
