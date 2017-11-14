package cn.edu.nju.dzy.dto.mapper;

import cn.edu.nju.dzy.dto.UserPassDTO;
import org.springframework.stereotype.Component;


@Component
public class ThirdPointIndecsDTOMapper {

	
	public UserPassDTO toDTO(String username){
		UserPassDTO userPassDTO=new UserPassDTO();
		userPassDTO.setUsername(username);
		return userPassDTO;
	}

}
