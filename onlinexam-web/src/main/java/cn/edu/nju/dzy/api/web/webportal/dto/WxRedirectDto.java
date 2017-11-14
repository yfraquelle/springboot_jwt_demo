package cn.edu.nju.dzy.api.web.webportal.dto;

public class WxRedirectDto {

	private String role;
	private String token;
	private Long userId;
	private String displayName;
	private String redirecturl;

	public WxRedirectDto(String role, String token, Long userId, String displayName, String redirecturl) {
		this.role = role;
		this.token = token;
		this.userId = userId;
		this.displayName = displayName;
		this.redirecturl = redirecturl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getRedirecturl() {
		return redirecturl;
	}

	public void setRedirecturl(String redirecturl) {
		this.redirecturl = redirecturl;
	}
}
