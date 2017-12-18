package cn.edu.nju.dzy.api.web.webportal.controller;

import java.util.List;

public class Question {
    public int order;
    public int level;
    public String type;
    public String stem;
    public List<String> options;
    public List<String> answers;
    public List<String> actualchoices;
    public int questionScore;
    public int actualQuestionScore;
    public boolean marked;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public List<String> getActualchoices() {
        return actualchoices;
    }

    public void setActualchoices(List<String> actualchoices) {
        this.actualchoices = actualchoices;
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(int questionScore) {
        this.questionScore = questionScore;
    }

    public int getActualQuestionScore() {
        return actualQuestionScore;
    }

    public void setActualQuestionScore(int actualQuestionScore) {
        this.actualQuestionScore = actualQuestionScore;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
