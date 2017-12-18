package cn.edu.nju.dzy.security;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import cn.edu.nju.dzy.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.dzy.domain.User;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Inject
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
//        Optional<User> userFromDatabase = userRepository.findOneByLogin(lowercaseLogin);
//        return userFromDatabase.map(user -> {
//            if (!user.getActivated()) {
//                throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
//            }
            List<GrantedAuthority> grantedAuthorities=null;
            if(login.startsWith("student")) {
                String[] student_role=new String[1];
                student_role[0]="ROLE_student";
                grantedAuthorities = Arrays.asList(student_role).stream()
                        .map(authority -> new SimpleGrantedAuthority("ROLE_student"))
                        .collect(Collectors.toList());
            }
            else if(login.startsWith("admin")) {
                    String[] admin_role=new String[1];
                    admin_role[0]="ROLE_admin";
                    grantedAuthorities = Arrays.asList(admin_role).stream()
                            .map(authority -> new SimpleGrantedAuthority("ROLE_admin"))
                            .collect(Collectors.toList());
            }
            else if(login.startsWith("exam")) {
                String[] admin_role=new String[1];
                admin_role[0]="ROLE_exam";
                grantedAuthorities = Arrays.asList(admin_role).stream()
                        .map(authority -> new SimpleGrantedAuthority("ROLE_exam"))
                        .collect(Collectors.toList());
            }
            return new org.springframework.security.core.userdetails.User(lowercaseLogin,
                "password",
                grantedAuthorities);
//			//TODO: MT:  return new CustomUserDetails(userFromDatabase.get(), grantedAuthorities);
//        }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " +
//        "database"));
    }
}
