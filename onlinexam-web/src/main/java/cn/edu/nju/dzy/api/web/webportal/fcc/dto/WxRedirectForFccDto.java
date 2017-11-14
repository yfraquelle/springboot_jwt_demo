package cn.edu.nju.dzy.api.web.webportal.fcc.dto;

/**
 * Created by mdw on 2017/7/15.
 */
public class WxRedirectForFccDto {
    private String status;  //http 状态码
    private String result;  //成功返回：success；失败返回：fail
    private String message; //消息
    private String company; //公司名称
    private String companyId; //公司ID
    private String wxname;  //用户名称
    private String uid;     //用户登录名称
    private String realName;  //真实姓名
    private boolean isactive;  //激活状态

    public WxRedirectForFccDto() {
    }

    public WxRedirectForFccDto(String status, String result, String message, String company, String companyId, String wxname, String uid, String realName, boolean isactive) {
        this.status = status;
        this.result = result;
        this.message = message;
        this.company = company;
        this.companyId = companyId;
        this.wxname = wxname;
        this.uid = uid;
        this.realName = realName;
        this.isactive = isactive;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public boolean isactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
