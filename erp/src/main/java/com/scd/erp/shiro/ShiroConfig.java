package com.scd.erp.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {
    @Value("${isDevelop}")
    private boolean isdev;

    //创建ShiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        Map<String, String> fMap = new HashMap<String, String>();
        if (isdev){
        shiroFilterFactoryBean.setLoginUrl("/index.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/permission.html");
        fMap.put("/login.html", "anon");
        fMap.put("/static/login.html", "anon");
        fMap.put("/log4j.properties", "anon");
        fMap.put("/static/**", "anon");
        fMap.put("/templates/**", "anon");
        fMap.put("/login.do", "anon");
        fMap.put("/**", "authc");
        }else{
            fMap.put("/**", "anon");
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(fMap);
        return shiroFilterFactoryBean;

    }



    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(getUserRealm());
        return securityManager;
    }


    @Bean(name = "userRealm")
    public UserRealm getUserRealm() {
        return new UserRealm();
    }



    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

}

