package cn.edu.nju.dzy.web.rest.dto;

import java.util.Objects;

public class UserInfoDTO {


	    private Long id;
	    private String uid;

	    private String email;

	    private String password;

	    private String wxid;
	    
	    private String unionid;

	    private String companyName;

	    private String departName;

	    private String title;

	    private String displayName;//账号申请中的昵称

		private String realName;

	    private String serialNo;


	    private String phone;

	    private Integer status;

	    private String note;

	    private String code;

	    public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}


	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getUid() {
	        return uid;
	    }

	    public void setUid(String uid) {
	        this.uid = uid;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
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

	    public String getDepartName() {
	        return departName;
	    }

	    public void setDepartName(String departName) {
	        this.departName = departName;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getDisplayName() {
	        return displayName;
	    }

	    public void setDisplayName(String displayName) {
	        this.displayName = displayName;
	    }

	    public String getSerialNo() {
	        return serialNo;
	    }

	    public void setSerialNo(String serialNo) {
	        this.serialNo = serialNo;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public Integer getStatus() {
	        return status;
	    }

	    public void setStatus(Integer status) {
	        this.status = status;
	    }

	    public String getNote() {
	        return note;
	    }

	    public void setNote(String note) {
	        this.note = note;
	    }

	


	    @Override
	    public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (o == null || getClass() != o.getClass()) {
	            return false;
	        }
	        UserInfoDTO userInfo = (UserInfoDTO) o;
	        if(userInfo.id == null || id == null) {
	            return false;
	        }
	        return Objects.equals(id, userInfo.id);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hashCode(id);
	    }

	    @Override
	    public String toString() {
	        return "UserInfo{" +
	            "id=" + id +
	            ", uid='" + uid + "'" +
	            ", email='" + email + "'" +
	            ", password='" + password + "'" +
	            ", wxid='" + wxid + "'" +
	            ", companyName='" + companyName + "'" +
	            ", departName='" + departName + "'" +
	            ", title='" + title + "'" +
	            ", displayName='" + displayName + "'" +
	            ", serialNo='" + serialNo + "'" +
	            ", phone='" + phone + "'" +
	            ", status='" + status + "'" +
	            ", note='" + note + "'" +
	            '}';
	    }

		public String getUnionid() {
			return unionid;
		}

		public void setUnionid(String unionid) {
			this.unionid = unionid;
		}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
