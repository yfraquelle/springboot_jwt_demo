package cn.edu.nju.dzy.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A UserInfo.
 */
@Entity
@Table(name = "user_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "uid")
	private String uid;//用户名

	@Column(name = "realname")
	private String realName;//用户姓名

	@Column(name = "email")
	private String email;//邮件

	@Column(name = "password")
	private String password;

	@Column(name = "serial_no")
	private String serialNo;//学号

	@Column(name = "phone")
	private String phone;//手机号

	@Column(name = "status")
	private Integer status;//用户状态，可能值为UserInfoConst中值，STATUS_ACTIVE=1，STATUS_DISABLE=-1，STATUS_REG=0

	@Column(name = "note")
	private String note;//备注

	@Transient
	private String code;

	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(unique = true)
	private User user;

	@Column(name = "tenant_id")
	@JsonIgnore
	private Long tenantId;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
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

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserInfo userInfo = (UserInfo) o;
		if (userInfo.id == null || id == null) {
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
				", uid='" + uid + '\'' +
				", realName='" + realName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", serialNo='" + serialNo + '\'' +
				", phone='" + phone + '\'' +
				", status=" + status +
				", note='" + note + '\'' +
				", code='" + code + '\'' +
				", user=" + user +
				", tenantId=" + tenantId +
				'}';
	}


	public boolean copy(UserInfo userInfoNew){
		this.code= userInfoNew.getCode();
		this.email=userInfoNew.getEmail();
		this.note=userInfoNew.getNote();
		this.password=userInfoNew.getPassword();
		this.phone=userInfoNew.getPhone();
		this.serialNo=userInfoNew.getSerialNo();
		this.status=userInfoNew.getStatus();
		this.tenantId=userInfoNew.getTenantId();
		this.uid=userInfoNew.getUid();
		this.realName = userInfoNew.getRealName();

		this.user.setActivated(userInfoNew.getUser().getActivated());
		this.user.setActivationKey(userInfoNew.getUser().getActivationKey());
		this.user.setAuthorities(userInfoNew.getUser().getAuthorities());
		//this.user.setCreatedBy(userInfoNew.getUser().getCreatedBy());
		//this.user.setCreatedDate(userInfoNew.getUser().getCreatedDate());
		this.user.setEmail(userInfoNew.getUser().getEmail());
		this.user.setFirstName(userInfoNew.getUser().getFirstName());
		this.user.setLangKey(userInfoNew.getUser().getLangKey());
		//this.user.setLastModifiedBy(userInfoNew.getUser().getLastModifiedBy());
		//this.user.setLastModifiedDate(userInfoNew.getUser().getLastModifiedDate());
		this.user.setLastName(userInfoNew.getUser().getLastName());
		this.user.setLogin(userInfoNew.getUser().getLogin());
		this.user.setPassword(userInfoNew.getUser().getPassword());
		//this.user.setPersistentTokens(userInfoNew.getUser().getPersistentTokens());
		//this.user.setResetDate(userInfoNew.getUser().getResetDate());
		//this.user.setResetKey(userInfoNew.getUser().getResetKey());
		
		return true;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
