package cn.edu.nju.dzy.api.web.webportal.dto;

/**
 * Created by mdw on 2017/4/15.
 */
public class RedirectDto {
    private String jwt;
    private String redirecturl;
    private String username;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRedirecturl() {
        return redirecturl;
    }

    public void setRedirecturl(String redirecturl) {
        this.redirecturl = redirecturl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
