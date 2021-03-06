package cn.edu.nju.dzy.dto;

import cn.edu.nju.dzy.domain.Authority;
import cn.edu.nju.dzy.domain.User;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

public class WxUserDTO {
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    @Pattern(regexp = "^[a-z0-9]*$")
    @NotNull
    @Size(min = 1, max = 50)
    private String login;

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private boolean activated = false;
    
    @Size(max = 20)
   
    private String activationKey;//随机生成，用于生成用户注册链接

    @Size(min = 2, max = 5)
    private String langKey;

    private Set<String> authorities;

    public WxUserDTO() {
    }

    public WxUserDTO(User user) {
        this(user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(),
            user.getEmail(), user.getActivated(), user.getActivationKey(),user.getLangKey(),
            user.getAuthorities().stream().map(Authority::getName)
                .collect(Collectors.toSet()));
    }

    public WxUserDTO(String login, String password, String firstName, String lastName,
                     String email, boolean activated, String activationKey, String langKey, Set<String> authorities) {

        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.activationKey=activationKey;
        this.langKey = langKey;
        this.authorities = authorities;
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
    	this.password = password;
    }
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login){
    	this.login=login;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName){
    	this.firstName=firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName){
    	this.lastName=lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
    	this.email=email;
    }

    public boolean isActivated() {
        return activated;
    }
    
    public void setActivated(boolean activated){
    	this.activated=activated;
    }

    public String getLangKey() {
        return langKey;
    }
    public void setLanKey(String lanKey){
    	this.langKey=lanKey;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }
    
    public void setAuthorities(Set<String> authorities){
    	this.authorities=authorities;
    }
    
    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    @Override
    public String toString() {
        return "WxUserDTO{" +
            "login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", activated=" + activated +
            ", activationKey=" + activationKey +
            ", langKey='" + langKey + '\'' +
            ", authorities=" + authorities +
            "}";
    }

}
