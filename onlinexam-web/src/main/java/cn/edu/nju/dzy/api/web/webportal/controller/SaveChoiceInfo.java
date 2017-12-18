package cn.edu.nju.dzy.api.web.webportal.controller;

import java.util.List;
import java.util.Set;

public class SaveChoiceInfo {
    public int order;
    public Set<String> choices;
    public boolean marked;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Set<String> getChoices() {
        return choices;
    }

    public void setChoices(Set<String> choices) {
        this.choices = choices;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}
