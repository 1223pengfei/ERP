package com.scd.erp.controller.auth;

import com.scd.erp.Vo.Person.Person;
import com.scd.erp.Vo.Person.User;
import com.scd.erp.service.auth.PersonService;
import com.scd.erp.utils.common.EncryptUtils;
import com.scd.erp.utils.common.RedisUtils;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.LoginUtile;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping
@Controller
public class loginContro {
    private static final Logger logger = LoggerFactory.getLogger(loginContro.class);

    private final PersonService personService;
    private final RedisUtils redisUtils;

    @Autowired
    public loginContro(PersonService personService, RedisUtils redisUtils) {
        this.personService = personService;
        this.redisUtils = redisUtils;
    }

    @RequestMapping("/")
    public String hello(){
        System.out.println("开始使用");
        return "login.html";
    }

    //@RequiresPermissions("select:auth")
    @PostMapping("getAuths.do")
    @ResponseBody
    public Map getAuths(){
        return this.personService.getAuths();
    }

    @PostMapping("login")
    @ResponseBody
    public Map<String, Object> loginUsers(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         HttpServletRequest request, HttpServletResponse response) {
        return loginUser(username,password,request,response);
    }

        @PostMapping("/login.do")
    @ResponseBody
    public Map<String, Object> loginUser(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         HttpServletRequest request, HttpServletResponse response) {

        String msg = "",msg2;
        Subject subject = SecurityUtils.getSubject();
        Person p = new Person();

        if (!StringUtils.hasText(username)||!StringUtils.hasText(password)){
            msg = "需要输入账号密码";
            msg2 = "fail";
        }else {
            String newpassword = EncryptUtils.encryptMD5(password).toUpperCase();
            UsernamePasswordToken token = new UsernamePasswordToken(username, newpassword);
            //System.out.println(token);
            System.out.println("获取到信息，开始验证！！");
            token.setRememberMe(false);

            try {
                subject.login(token);
                subject.getSession().setTimeout(-900);
                User user = (User) subject.getPrincipal();
                HttpSession sessions = request.getSession();
                sessions.setAttribute("user", user);
                p = this.personService.getPersonAll(user.getPresonid());
                sessions.setAttribute("person", p);
                SessionsSecurityManager securityManager = (SessionsSecurityManager) SecurityUtils.getSecurityManager();
                SessionManager sessionManagers = securityManager.getSessionManager();
                DefaultSessionManager sessionManager = new DefaultSessionManager();
                securityManager.setSessionManager(sessionManagers);
                String sessionId = String.valueOf(sessions.getId());
                String sessionId2 = redisUtils.objToString(username + "_single");
                if (StringUtil.isNotEmpty(sessionId2)) {
                    SessionKey sessionKey = new WebSessionKey(redisUtils.objToString(username + "_single"), request, response);

                    Session session = securityManager.getSession(sessionKey);
                    if (!sessionId.equals(sessionId2))
                        sessionManager.getSessionDAO().delete(session);
                    redisUtils.delete(username + "_single");
                }
                redisUtils.set(username + "_single", sessionId);
            } catch (UnknownAccountException uae) {
                msg = "未知账户";
                logger.error(uae.toString());
            } catch (IncorrectCredentialsException ice) {
                msg = "密码不正确";
                logger.error(ice.toString());
            } catch (LockedAccountException lae) {
                msg = "账户已锁定";
                logger.error(lae.toString());
            } catch (ExcessiveAttemptsException eae) {
                msg = "用户名或密码错误次数过多";
                logger.error(eae.toString());
            } catch (AuthenticationException ae) {
                msg = "用户名或密码不正确！";
                logger.error(ae.toString());
            }

            if (subject.isAuthenticated()) {
                msg = "登录成功";
                msg2 = "ok";
            } else {
                token.clear();
                msg += "!登录失败!!";
                msg2 = "fail";
            }

        }
       //s System.out.println(msg);
        return new ChainMap<>().putObj("data", msg)
                .putObj("msg", msg2)
                .putObj("person", p);

    }

    @PostMapping("/logout.do")
    @ResponseBody
    public String logout() {
        Subject subject = SecurityUtils.getSubject();//取出当前验证主体
        //System.out.println(subject);
        if (subject != null) {
            subject.logout();//不为空，执行一次logout的操作，将session全部清空
        }
        return "login";
    }
    private LoginUtile loginUtile = new LoginUtile();

    @PostMapping("pwd.do")
    @ResponseBody
    public void setWord(HttpSession session,String password){
        // ConsoleMsg.log(password);
        this.personService.setWord(loginUtile.getUser(session).getUserid(), EncryptUtils.encryptMD5(password).toUpperCase());
        logout();
    }

}
