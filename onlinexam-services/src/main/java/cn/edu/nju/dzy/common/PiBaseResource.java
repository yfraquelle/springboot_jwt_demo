package cn.edu.nju.dzy.common;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import cn.edu.nju.dzy.config.JHipsterProperties;
import cn.edu.nju.dzy.domain.Authority;
import cn.edu.nju.dzy.domain.PoConstant;
import cn.edu.nju.dzy.dto.WxUserInfoDTO;
import cn.edu.nju.dzy.dto.mapper.WxUserInfoDTOMapper;
import cn.edu.nju.dzy.repository.UserRepository;
import cn.edu.nju.dzy.security.AuthoritiesConstants;
import cn.edu.nju.dzy.security.SecurityUtils;
import cn.edu.nju.dzy.security.jwt.JWTConfigurer;
import cn.edu.nju.dzy.security.SecurityCourier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import cn.edu.nju.dzy.utils.DateUtil;
import cn.edu.nju.dzy.config.Constants;
import cn.edu.nju.dzy.domain.User;
import cn.edu.nju.dzy.domain.UserInfo;
import cn.edu.nju.dzy.repository.UserInfoRepository;
import cn.edu.nju.dzy.security.jwt.TokenProvider;


public class PiBaseResource extends ResourceController{
	private final String SEND_USER_TO_NEW_COMPANY_PATH="/portal/wx/sendusertonewcompany/";
	private final Logger log = LoggerFactory.getLogger(PiBaseResource.class);

	
	@Inject
	private UserRepository userRepository;
	@Inject
	WxUserInfoDTOMapper wxUserInfoDTOMapper;
	@Inject
	JHipsterProperties jhipsterProperteis;
	
	@Inject
	protected UserInfoRepository userInfoRepository;
	
	@Inject
	protected AuthenticationManager authenticationManager;

	@Inject
	protected TokenProvider tokenProvider;

	@Inject
	SecurityCourier securityCourier;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addCustomFormatter(new DateFormatter(DateUtil.LOCAL_DATE_PATTERN));
	}
	

	
	protected boolean isLogined(){
		String login = SecurityUtils.getCurrentUserLogin();
		if (login == null || Constants.USER_ANONYMOUSUSER.equals(login)){
			return false;
		} else {
			return true;
		}
	}
	
	protected boolean isAdmin(){
		String login = SecurityUtils.getCurrentUserLogin();
		if (login == null ){
			return false;
		} else {
			return SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN);
			
		}
	}
	
	protected boolean isSysAdmin(){
		String login = SecurityUtils.getCurrentUserLogin();
		if (login == null ){
			return false;
		} else {
			return SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.SYSADMIN);
			
		}
	}
	

	
	private int setAuthByOauth(UserInfo wuser ,HttpServletRequest request, HttpServletResponse resp){
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				wuser.getUid(), wuser.getPassword());
		try {
		
			Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			// boolean rememberMe = (loginDTO.isRememberMe() == null) ? false :
			// wuser.isRememberMe();
			String jwt = tokenProvider.createToken(authentication, true);
			resp.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
			
		} catch (AuthenticationException ex) {
			log.error("Auth exception",ex);
			return HttpStatus.UNAUTHORIZED.value();
		}
		return 0;
	}
	
	
	protected UserInfo getRequestUserInfo() {//从jwt中获得用户信息
		String login = SecurityUtils.getCurrentUserLogin();
		UserInfo userInfo;
		log.debug("REST request to get login :" + login );

		if (login == null || "anonymousUser".equals(login)){
			log.debug("Don't have auther info and no oauth code");
			userInfo = null;
		}  else { //aready have the auther info
			Optional<UserInfo> userInfoOp =  userInfoRepository.findOneByUid(login);
			if (userInfoOp.isPresent()){
				userInfo = userInfoOp.get();
			} else {
				
				userInfo = null;
			}
		}
		log.debug("Find userInfo from Session:" + userInfo);
		return userInfo;
	}
	@Transactional
	protected UserInfo getRequestUserInfo(HttpServletRequest request) {//从header中获得用户信息，若本地DB中没有此用户，则从portal处拉取用户信息并保存在本地，仅本地有此用户且此用户已激活时才不返回null
		String login = SecurityUtils.getCurrentUserLogin();
		UserInfo userInfo;
		log.debug("REST request to get login :" + login );

		if (login == null || "anonymousUser".equals(login)){
			log.debug("have no auther info and no oauth code");
			String uid=request.getHeader("uid");
			if(uid==null||uid.isEmpty()){
				log.info("PiBaseResource getRequestUserInfo: (login == null || 'anonymousUser'.equals(login)) && openid==null||openid.isEmpty()");
				userInfo = null;
				return null;
			}
			else{
				Optional<UserInfo> userInfoOp=userInfoRepository.findOneByUid(uid);
				if(!userInfoOp.isPresent()){
					log.info("PiBaseResource getRequestUserInfo: wxUserInfoDTO=getUserFromPortal(openid) is null");
					return null;
				}
				else{					
					userInfo=userInfoOp.get();
					if(!userInfo.getUser().getActivated()){
						log.info("PiBaseResource getRequestUserInfo: have no auther info and !userInfo.getUser().getActivated()");
						userInfo=null;
						return null;
					}
				}
			}
		}  else { //aready have the auther info
			Optional<UserInfo> userInfoOp =  userInfoRepository.findOneByUid(login);
			if (userInfoOp.isPresent()){
				userInfo = userInfoOp.get();
				if(!userInfo.getUser().getActivated()){
					log.info("PiBaseResource getRequestUserInfo: have auther info and !userInfo.getUser().getActivated()");
					userInfo=null;
					return null;
				}
			} else {//本地数据库没找到此用户的uid，说明本地数据库中没此用户
				String openid=request.getHeader("openid");//获得传入的openid
				if(openid==null||openid.isEmpty()){
					log.info("PiBaseResource getRequestUserInfo:can't find openid from request");
					userInfo = null;
					return null;
				}
				else{
					log.info("PiBaseResource getRequestUserInfo: wxUserInfoDTO=getUserFromPortal(openid) is null");
					return null;
				}
			}
		}
		log.debug("Find userInfo from Session:" + userInfo);
		return userInfo;
	}
	
	protected UserInfo getUserInfoFromJwt(HttpServletRequest request ,HttpServletResponse respons) {
			String login = SecurityUtils.getCurrentUserLogin();
			UserInfo userInfo;
			log.debug("REST request to get login :" + login );

			if (login == null || "anonymousUser".equals(login)){
				log.debug("Don't have auther info and no oauth code");
				userInfo = null;
			}  else { //aready have the auther info
				Optional<UserInfo> userInfoOp =  userInfoRepository.findOneByUid(login);
				if (userInfoOp.isPresent()){
					userInfo = userInfoOp.get();
				} else {
					
					userInfo = null;
				}
			}
			log.debug("Find userInfo from Session:" + userInfo);
			return userInfo;
	}
	
	protected UserInfo getUserByUid(String uid,boolean rememberMe, HttpServletRequest request ,HttpServletResponse response){
		Optional<UserInfo> userInfoOp = userInfoRepository.findOneByUid(uid);
		UserInfo userInfo = null;
		if (userInfoOp.isPresent()){
			userInfo = userInfoOp.get();
			if (rememberMe){
				int ret = setAuthByOauth(userInfo, request, response);
				if (ret !=0){
					log.error("Error of set Auth :" +ret);
				}
			}
		} else {
			log.debug("find userInfo faild");
			userInfo = null;
		}
		return userInfo;
	}


}
