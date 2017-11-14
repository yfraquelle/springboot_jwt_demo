package cn.edu.nju.dzy.dto.mapper;

import cn.edu.nju.dzy.domain.Authority;
import cn.edu.nju.dzy.domain.User;
import cn.edu.nju.dzy.dto.WxUserDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class WxUserDTOMapper {
	
	public WxUserDTO toDTO(User user){
		WxUserDTO wxUserDTO=new WxUserDTO(user);
		return wxUserDTO;		
	}
	//TODO ADD fromDTO
	public User fromDTO(WxUserDTO wxUserDTO){
		User user=new User();
		user.setActivationKey(wxUserDTO.getActivationKey()); 
		user.setActivated(wxUserDTO.isActivated()); 
		Set<String>authorities_s= wxUserDTO.getAuthorities();
		Set<Authority> authorities_a=new HashSet<>();
		if(authorities_s.iterator().hasNext()){
			Iterator <String>iterator=authorities_s.iterator();
			while(iterator.hasNext()){
				authorities_a.add(new Authority(iterator.next()));
			}
			
		}
		user.setAuthorities(authorities_a);
		user.setEmail(wxUserDTO.getEmail()); 
		user.setFirstName( wxUserDTO.getFirstName());
		user.setLangKey(wxUserDTO.getLangKey()); 
		user.setLastName(wxUserDTO.getLastName()); 
		user.setLogin(wxUserDTO.getLogin());
		user.setPassword(wxUserDTO.getPassword()); 
		return user;
	}
}
