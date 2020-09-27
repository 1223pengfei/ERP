package com.scd.erp.config;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ControAdvice {
        @ExceptionHandler(UnauthorizedException.class)
        public String handleShiroException(Exception ex) {
            //ex.printStackTrace();
            return "permission.html";
        }
        @ExceptionHandler(AuthorizationException.class)
        public String AuthorizationException(Exception ex) {
            //ex.printStackTrace();
            return "permission.html";
        }
}
