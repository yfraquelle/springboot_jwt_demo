package cn.edu.nju.dzy.dto;


import java.util.Objects;

//将porta处生成的用户相关信息POST到各企业处
public class WxUserInfoDTO {
	private String phone;
	private String email;
	private String uid;//000用户名
	private String password;//0

	
	private String wxid;//00用户的openid

	
	private String unionid;//00
	
	private String companyName;//公司名
	
	private Long companyId;//company表中的company_id

	private String registerCompanyName;

	private String realName;

	private String displayName;//00称呼

	
	private Integer status;//用户状态，可能值为UserInfoConst中值，STATUS_ACTIVE=1，STATUS_DISABLE=-1，STATUS_REG=0


	
	private WxUserDTO wxUserDTO;//TODO 改为DTO




	public Long getCompanyId(){
		return companyId;
	}
	public void setCompanyId(Long companyId){
		this.companyId=companyId;
	}
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}




	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWxid() {
		return wxid;
	}

	public void setWxid(String wxid) {
		this.wxid = wxid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	

	public WxUserDTO getwxUserDTO() {
		return wxUserDTO;
	}

	public void setwxUserDTO(WxUserDTO wxUserDTO) {
		this.wxUserDTO = wxUserDTO;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		WxUserInfoDTO userInfo = (WxUserInfoDTO) o;
		if (userInfo.uid == null || uid == null) {
			return false;
		}
		return Objects.equals(uid, userInfo.uid);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(uid);
	}

	@Override
	public String toString() {
		return "WxUserInfoDTO [id=" + "" + ", uid=" + uid + ", email=" + "" + ", password=" + password + ", wxid=" + wxid
				+ ", unionid=" + unionid + ", companyName=" + companyName  + ", departName="
				+ "" + ", departId=" + "" + ", title=" + "" + ", displayName=" + displayName
				+ ", serialNo=" + "" + ", phone=" + "" + ", status=" + status + ", note=" + "" + ", code="
				+ "" + ", WxUserDTO=" + wxUserDTO + ", tenantId=" + "" + "]";
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegisterCompanyName() {
		return registerCompanyName;
	}

	public void setRegisterCompanyName(String registerCompanyName) {
		this.registerCompanyName = registerCompanyName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
