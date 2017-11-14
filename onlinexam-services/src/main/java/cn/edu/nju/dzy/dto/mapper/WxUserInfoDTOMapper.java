package cn.edu.nju.dzy.dto.mapper;

import cn.edu.nju.dzy.domain.User;
import cn.edu.nju.dzy.domain.UserInfo;
import cn.edu.nju.dzy.dto.WxUserDTO;
import cn.edu.nju.dzy.dto.WxUserInfoDTO;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

;

//UserInfo转换为DTO
@Component
public class WxUserInfoDTOMapper {
	
	@Inject
	WxUserDTOMapper wxUserDTOMapper;

//map function	
	public WxUserInfoDTO toDTO(UserInfo userInfo){
		WxUserInfoDTO wxUserInfoDTO=new WxUserInfoDTO();

		wxUserInfoDTO.setEmail(userInfo.getEmail());
		wxUserInfoDTO.setPhone(userInfo.getPhone());
		wxUserInfoDTO.setPassword(userInfo.getPassword());
		wxUserInfoDTO.setStatus(userInfo.getStatus());
		wxUserInfoDTO.setUid(userInfo.getUid());
		wxUserInfoDTO.setRealName(userInfo.getRealName());

		WxUserDTO wxUserDTO=new WxUserDTO(userInfo.getUser());
		wxUserInfoDTO.setwxUserDTO(wxUserDTO);
		
		
		return wxUserInfoDTO;
		
	}
	//TODO 完善fromDTO
	public UserInfo fromDTO(WxUserInfoDTO wxUserInfoDTO){
		UserInfo userInfo=new UserInfo();
		userInfo.setEmail(wxUserInfoDTO.getEmail());
		userInfo.setPhone(wxUserInfoDTO.getPhone());
		userInfo.setPassword(wxUserInfoDTO.getPassword()); 
		userInfo.setStatus(wxUserInfoDTO.getStatus()); 
		userInfo.setUid(wxUserInfoDTO.getUid());
		userInfo.setRealName(wxUserInfoDTO.getRealName());
		WxUserDTO wxUserDTO=wxUserInfoDTO.getwxUserDTO();
		User user=wxUserDTOMapper.fromDTO(wxUserDTO);
		userInfo.setUser(user);
		return userInfo;
	}
}
