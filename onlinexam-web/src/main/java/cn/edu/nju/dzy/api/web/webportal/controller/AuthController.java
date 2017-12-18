package cn.edu.nju.dzy.api.web.webportal.controller;

import cn.edu.nju.dzy.common.PiBaseResource;
import cn.edu.nju.dzy.config.JHipsterProperties;
import cn.edu.nju.dzy.repository.UserInfoRepository;
import cn.edu.nju.dzy.security.jwt.JWTConfigurer;
import cn.edu.nju.dzy.security.jwt.TokenProvider;
import cn.edu.nju.dzy.web.rest.JWTToken;
import cn.edu.nju.dzy.web.rest.errors.ErrorDTO;
import com.codahale.metrics.annotation.Timed;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mdw
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends PiBaseResource{
    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    UserInfoRepository userInfoRepository;


    @Inject
    JHipsterProperties jhipsterProperteis;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Timed
    @Transactional
    public RegisterResponse login(@RequestBody Credential credential, HttpServletRequest request, HttpServletResponse response) {
        log.info(credential.studentId);
        RegisterResponse loginResponse=new RegisterResponse();
        LoginData loginData=new LoginData();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(credential.getStudentId(), credential.getPassword());
        if(credential.studentId.startsWith("student")){
            loginData.setRole("student");
        }
        else if(credential.studentId.startsWith("admin")){
            loginData.setRole("admin");
        }
        loginData.setStudentId(credential.studentId);
        loginData.setName("张三");
        loginData.setEmail("example@smail.nju.edu.cn");
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, true);
            loginData.setToken(jwt);
            loginResponse.setData(loginData);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, jwt);
            return loginResponse;
        } catch (AuthenticationException exception) {
            loginResponse.setCode(305);
            return loginResponse;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Timed
    @Transactional
    public RegisterResponse register(@RequestBody RegisterInfo registerInfo,HttpServletRequest request, HttpServletResponse response) {
        log.info(registerInfo.email);
        return new RegisterResponse();
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    @Timed
    @Transactional
    public RegisterResponse token(@RequestBody Credential credential, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(credential.getStudentId(), credential.getPassword());
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, true);
            RegisterResponse registerResponse=new RegisterResponse();
            registerResponse.setData(jwt);
            return registerResponse;
        } catch (AuthenticationException exception) {
            return null;
        }
    }
}
