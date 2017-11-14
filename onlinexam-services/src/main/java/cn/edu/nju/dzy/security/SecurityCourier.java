package cn.edu.nju.dzy.security;

import cn.edu.nju.dzy.config.JHipsterProperties;
import cn.edu.nju.dzy.security.jwt.JWTConfigurer;
import cn.edu.nju.dzy.domain.UserInfo;
import cn.edu.nju.dzy.repository.UserInfoRepository;
import cn.edu.nju.dzy.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by YU Fan on 2017/5/22.
 */
@Component
public class SecurityCourier {
    private Logger log= LoggerFactory.getLogger(SecurityCourier.class);

    @Inject
    UserInfoRepository userInfoRepository;

    @Inject
    JHipsterProperties jhipsterProperteis;

    @Inject
    AuthenticationManager authenticationManager;

    @Inject
    TokenProvider tokenProvider;

    private SimpleClientHttpRequestFactory requestFactory;
    private RestTemplate restTemplate;
    private String wxUrl;

    public void setCourier(String url)
    {
        requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60000);
        requestFactory.setReadTimeout(60000);
        restTemplate = new RestTemplate(requestFactory);
        wxUrl =url;
    }

    public void setCourier(int connectTimeout,int readTimeout,String url)
    {
        requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate = new RestTemplate(requestFactory);
        wxUrl =url;
    }

    public <T> T sendRequest(UserInfo userInfo, HttpMethod method, Object requestBody, Class<T> responseType) throws CourierException
    {
        String jwt=generateJWT(userInfo);
        if(jwt==null)
        {
            throw new CourierException("jwt is null");
        }
        HttpHeaders requestHeaders=new HttpHeaders();
        requestHeaders.set(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        HttpEntity request=null;
        if(requestBody==null)
        {
            request=new HttpEntity<>(requestHeaders);
        }
        else {
            request = new HttpEntity<>(requestBody, requestHeaders);
        }
        try {
            log.info("url is : "+wxUrl);
            ResponseEntity<T> responseEntity = restTemplate.exchange(wxUrl, method,request,responseType);
            if(responseEntity.getStatusCode()== HttpStatus.OK)
            {
                return responseEntity.getBody();
            }
            else
            {
                throw new CourierException(responseEntity.getStatusCode()+" and failed");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
         //   throw new CourierException("get for object fail");
            throw new CourierException("Sysamdin 通信出现问题"+e.getMessage());
        }
    }
    public <T> ResponseEntity<T>  getResponseEntity(UserInfo userInfo, HttpMethod method, Object requestBody, Class<T> responseType) throws CourierException
    {
        String jwt=generateJWT(userInfo);
        if(jwt==null)
        {
            throw new CourierException("jwt is null");
        }
        log.info("jwt is "+jwt);
        log.info("url is "+wxUrl);
        HttpHeaders requestHeaders=new HttpHeaders();
        requestHeaders.set(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        HttpEntity request=null;
        if(requestBody==null)
        {
            request=new HttpEntity<>(requestHeaders);
        }
        else {
            request = new HttpEntity<>(requestBody, requestHeaders);
        }
        log.info(request.toString());
        try {
            log.info("send request");
            ResponseEntity responseEntity = restTemplate.exchange(wxUrl, method,request,responseType);
            log.info("receive response");
            return responseEntity;
        }catch(Exception e)
        {
            e.printStackTrace();
            throw new CourierException("get for object failed");
        }
    }

    private String generateJWT(UserInfo userInfo)
    {
        String token=null;
        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo.getUid(), userInfo.getPassword());
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
            boolean rememberMe = true;
            token = tokenProvider.createToken(authentication, rememberMe);
            log.info("token is "+token);
        } catch (AuthenticationException ex) {
            log.error("Auth exception",ex);
        }
        return token;
    }

    public SimpleClientHttpRequestFactory getRequestFactory() {
        return requestFactory;
    }

    public void setRequestFactory(SimpleClientHttpRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWxUrl() {
        return wxUrl;
    }

    public void setWxUrl(String wxUrl) {
        this.wxUrl = wxUrl;
    }
}
