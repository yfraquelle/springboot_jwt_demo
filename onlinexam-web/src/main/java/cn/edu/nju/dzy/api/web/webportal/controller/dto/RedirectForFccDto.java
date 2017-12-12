package cn.edu.nju.dzy.api.web.webportal.controller.dto;

/**
 * Created by mdw on 2017/7/16.
 */
public class RedirectForFccDto {
    private String jwt;

    public RedirectForFccDto(String jwt) {
        this.jwt = jwt;
    }

    public RedirectForFccDto() {
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
