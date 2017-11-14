package cn.edu.nju.dzy.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by YU Fan on 2017/5/22.
 */
public class CourierException  extends AuthenticationException {

    public CourierException(String message) {
        super(message);
    }

    public CourierException(String message, Throwable t) {
        super(message, t);
    }
}