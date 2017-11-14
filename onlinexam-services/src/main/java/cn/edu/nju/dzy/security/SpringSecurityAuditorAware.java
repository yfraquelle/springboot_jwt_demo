package cn.edu.nju.dzy.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import cn.edu.nju.dzy.config.Constants;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        String userName = SecurityUtils.getCurrentUserLogin();
        return (userName != null ? userName : Constants.SYSTEM_ACCOUNT);
    }
}
