package cn.edu.nju.dzy.security;

import java.util.Collection;
import java.util.Set;

import cn.edu.nju.dzy.domain.Authority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import cn.edu.nju.dzy.domain.User;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {
    private final static Logger log = LoggerFactory.getLogger(SecurityUtils.class);
    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
        if (authorities != null) {
            for (GrantedAuthority authority : authorities) {
                log.info(authority.toString());
                if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
                    return false;
                }
            }
        }
        log.info(authorities.toString());
        return true;
    }

    /**
     * If the current user has a specific authority (security role).
     *
     * <p>The name of this method comes from the isUserInRole() method in the Servlet API</p>
     *
     * @param authority the authorithy to check
     * @return true if the current user has the authority, false otherwise
     */
    public static boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                log.info(new SimpleGrantedAuthority(authority).toString());
                log.info(springSecurityUser.getAuthorities().toString());
                return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(authority));
            }
        }
        return false;
    }

	public static boolean isUserInRole(User user, String role) {
		Set<Authority> auths = user.getAuthorities();
		for (Authority auth:auths){
			if (role.equals(auth.getName())){
				return true;
			}
		}
		return false;
	}
}
