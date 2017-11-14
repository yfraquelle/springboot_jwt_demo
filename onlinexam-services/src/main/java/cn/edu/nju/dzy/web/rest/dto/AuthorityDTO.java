package cn.edu.nju.dzy.web.rest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthorityDTO {
	

	@NotNull
    @Size(min = 0, max = 50)
	private String name;
	
	public String getName() {
	     return name;
	 }

	 public void setName(String name) {
	     this.name = name;
	 }
	    @Override
	    public int hashCode() {
	        return name != null ? name.hashCode() : 0;
	    }

	    @Override
	    public String toString() {
	        return "AuthorityDTO{" +
	            "name='" + name + '\'' +
	            "}";
	    }
}
