package cn.edu.nju.dzy.api.web.webportal.controller;

import java.util.List;

public class AllExamsResponse {
    public int code=200;
    public String error_msg="";
    public List<Exam> exams;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
