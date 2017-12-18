package cn.edu.nju.dzy.api.web.webportal.controller;

import java.util.List;

public class ExamListResponse {
    public List<ExamInfo> data;
    public String error_msg;
    public int code;

    public List<ExamInfo> getData() {
        return data;
    }

    public void setData(List<ExamInfo> data) {
        this.data = data;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
