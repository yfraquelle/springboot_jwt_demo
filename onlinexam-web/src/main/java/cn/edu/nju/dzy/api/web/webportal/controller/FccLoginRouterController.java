package cn.edu.nju.dzy.api.web.webportal.controller;

import cn.edu.nju.dzy.common.PiBaseResource;
import cn.edu.nju.dzy.config.JHipsterProperties;
import cn.edu.nju.dzy.repository.UserInfoRepository;
import cn.edu.nju.dzy.security.AuthoritiesConstants;
import cn.edu.nju.dzy.security.jwt.TokenProvider;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 * Created by mdw
 */
@RestController
@RequestMapping("/api")
public class FccLoginRouterController extends PiBaseResource{
    private final static Logger log = LoggerFactory.getLogger(FccLoginRouterController.class);

    @Inject
    private TokenProvider tokenProvider;

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    UserInfoRepository userInfoRepository;


    @Inject
    JHipsterProperties jhipsterProperteis;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @Secured({AuthoritiesConstants.USER})
    public ResponseEntity<?> test(HttpServletResponse response) {
        return super.getOKResponse("ok","ok");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Timed
    @Transactional
    public ResponseEntity<?> login(@RequestBody Credential credential, HttpServletRequest request, HttpServletResponse response) {
        log.info(credential.username);
        return super.getOKResponse(credential,"OK");
    }

    @RequestMapping(value = "/registerY", method = RequestMethod.POST)
    @Timed
    @Transactional
    public ResponseEntity<?> register(@RequestBody RegisterInfo registerInfo,HttpServletRequest request, HttpServletResponse response) {
        log.info(registerInfo.email);
        return super.getOKResponse(registerInfo,"OK");
    }

}
