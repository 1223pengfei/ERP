package com.scd.erp.shiro;

import com.scd.erp.Vo.Department.Job;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.Vo.auth.Permission;
import com.scd.erp.Vo.auth.Rule;
import com.scd.erp.mapper.JobMapper;
import com.scd.erp.mapper.PermissionMapper;
import com.scd.erp.service.auth.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;



public class UserRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private PermissionMapper ruleMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
       Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
       // System.out.println("sub>>"+user);
        Set<String> set = new LinkedHashSet<>();
        Integer presonid =  user.getPresonid();
        //System.out.println(user);
        Job job =this.jobMapper.getJob(presonid);
       // System.out.println(job);
        if (null!=job ) {
        List<Rule> rules = job.getRules();

            for (Rule rule : rules) {
                List<Permission> premissions = rule.getPermissions();
                for (Permission pre : premissions) {
                    String url = pre.getPermissionurl();
                    // System.out.println("权限集合"+url);
                    set.add(url);
                }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(set);
        return simpleAuthorizationInfo;

    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        // TODO Auto-generated method stub

        UsernamePasswordToken user = (UsernamePasswordToken) arg0;
        User realUser = new User();
        realUser.setUsername(user.getUsername());
        realUser.setPassword(String.copyValueOf(user.getPassword()));
        //System.out.println(realUser);
        User newUser = loginService.login(realUser);
        if (newUser == null) {
            //用户名错误

            return null;
        }

        return new SimpleAuthenticationInfo(newUser, newUser.getPassword(), this.getClass().getName());
    }


}
