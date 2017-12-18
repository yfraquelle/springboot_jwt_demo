package cn.edu.nju.dzy.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String student = "ROLE_student";

    public static final String admin = "ROLE_admin";

    public static final String exam = "ROLE_exam";

    public static final String ANONYMOUS = "ANONYMOUS";
    
    private AuthoritiesConstants() {
    }
}
