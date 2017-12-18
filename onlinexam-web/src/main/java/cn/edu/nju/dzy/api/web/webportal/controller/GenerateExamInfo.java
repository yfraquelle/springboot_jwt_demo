package cn.edu.nju.dzy.api.web.webportal.controller;

import java.util.List;

public class GenerateExamInfo {
    public String courseName;
    public String startTime;
    public String endTime;
    public List<Setting> settingArray;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<Setting> getSettingArray() {
        return settingArray;
    }

    public void setSettingArray(List<Setting> settingArray) {
        this.settingArray = settingArray;
    }
}
