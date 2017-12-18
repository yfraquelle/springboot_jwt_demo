package cn.edu.nju.dzy.api.web.webportal.controller;

import java.util.List;

public class ExamPaper {
    public long examId;
    public String studentId;
    public String courseName;
    public String startTime;
    public String endTime;
    public int totalScore;
    public int actualScore;
    public List<Question> examQuestions;

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

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

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public List<Question> getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(List<Question> examQuestions) {
        this.examQuestions = examQuestions;
    }

    public int getActualScore() {
        return actualScore;
    }

    public void setActualScore(int actualScore) {
        this.actualScore = actualScore;
    }
}
