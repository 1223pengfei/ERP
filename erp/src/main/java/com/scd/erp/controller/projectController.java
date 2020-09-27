package com.scd.erp.controller;


import com.scd.erp.Vo.Person.Person;
import com.scd.erp.service.ProjectService;
import com.scd.erp.service.auth.PersonService;
import com.scd.erp.user.CountClass;
import com.scd.erp.user.Project;
import com.scd.erp.user.ProjectRole;
import com.scd.erp.utils.extraUtil.ChainMap;
import com.scd.erp.utils.extraUtil.LoginUtile;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "project/", method = RequestMethod.POST)
@Controller
public class projectController {

    private final ProjectService ps;
    private final PersonService personService;
    private LoginUtile lu;

    @Autowired
    public projectController(ProjectService ps, PersonService personService) {
        this.ps = ps;
        this.personService = personService;
        lu = new LoginUtile();
    }

    /**
     * @param project
     * @return
     * @throws Throwable 以下是项目主页接口
     */
    @RequiresPermissions("add:pro")
    @RequestMapping("/addProject.do")
    @ResponseBody
    public Boolean Register(Project project, HttpSession session) throws Throwable {
        Integer userid = lu.getUser(session).getUserid();
        project.setUid(userid);
        return ps.saveProject(project);
    }

    @RequiresPermissions("select:pro")
    @ResponseBody
    @RequestMapping("/selectProject.do")
    public List<Project> selectProject() throws Throwable {
        return ps.selectProject();
    }

    @RequiresPermissions("select:pro")
    @ResponseBody
    @RequestMapping("/selectProjectByuid.do")
    public List<Project> selectProjectByuid( HttpSession session) throws Throwable {
        Integer userid = lu.getUser(session).getUserid();
        return ps.selectProjectByuid(userid);
    }

    /**
     * 分页演示
     *
     * @param pid
     * @return
     * @throws Throwable
     */
//    public R1 ajaxBlog(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
//        PageHelper.startPage(pageNum,5);
//        List<Blog> blogList = blogMapper.selectByExample(new BlogExample());
//        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogList);
//        return R1.add("blog",pageInfo);
//    }
    @RequiresPermissions("del:pro")
    @ResponseBody
    @RequestMapping("/deleteProject.do")
    public Boolean delProject(Integer pid) throws Throwable {

        return ps.deleteProject(pid);
    }

    @RequiresPermissions("set:pro")
    @ResponseBody
    @RequestMapping("/updateProject.do")
    public Boolean updateProject(Project project) throws Throwable {

        return ps.updateProject(project);
    }

    /**
     * @param name
     * @return
     * @throws Throwable 以下是我的项目接口
     *                   --查询可添加人员 模糊查询
     */

    @ResponseBody
    @RequestMapping("/selectPerson.do")
    public List<Person> addPersonInProject(String name) throws Throwable {
        return this.personService.selectByname(name);
    }

    /**
     * 添加项目成员
     *
     * @param pid
     * @return
     * @throws Throwable
     */
    @RequiresPermissions("add:pro")
    @ResponseBody
    @RequestMapping("/addProjectPerson.do")
    public List<Project> addProjectPerson(Integer pid, Integer uid) throws Throwable {
       // System.out.println(pid+">>"+uid);
        return ps.addProjectPerson(pid, uid);
    }


    /**
     * @return
     * @throws Throwable 删除项目成员
     */
    @RequiresPermissions("del:pro")
    @ResponseBody
    @RequestMapping("/deleteProjectPerson.do")
    public List<Project> deleteProjectPerson(Integer uid, Integer pid) throws Throwable {

        return ps.deleteProjectPerson(uid, pid);
    }

    /**
     * @param projectRole
     * @return
     * @throws Throwable 修改团队项目角色
     */
    @RequiresPermissions("set:pro")
    @ResponseBody
    @RequestMapping("/updateProjectPerson.do")
    public List<Project> updateProjectPerson(ProjectRole projectRole, Integer pid) throws Throwable {

        List<Project> list = ps.updateProjectPerson(projectRole, pid);
        return list;
    }

    /**
     * @param pid
     * @return
     * @throws Throwable 查询团队信息
     */
    @RequiresPermissions("select:pro")
    @ResponseBody
    @RequestMapping("/selectALLProjectMsg.do")
    public List<Project> selectALLProjectMsg(Integer pid) throws Throwable {
        return ps.selectALLProjectMsg(pid);
    }

    /**
     * @param name
     * @return
     * @throws Throwable 关键字查询某人下面所有信息
     */
    @RequiresPermissions("get:pro")
    @ResponseBody
    @RequestMapping("/selectProjectByName.do")
    public List<Project> selectProjectByName(String name) throws Throwable {
        return ps.selectProjectByName(name);
    }


    /**
     * @param
     * @return
     * @throws Throwable 数据统计
     */
    @RequiresPermissions("count:pro")
    @ResponseBody
    @RequestMapping("/projectStatistics.do")
    public Map projectStatistics() throws Throwable {

        List<CountClass> list = ps.projectStatistics();
        double sum = list.stream().mapToDouble(CountClass::getCOUNT).sum();
        return new ChainMap<>().putObj("list",list).putObj("sum",sum);
    }

    /**
     * @param pname
     * @return
     * @throws Throwable 条件查询团队信息
     */
    @RequiresPermissions("get:pro")
    @ResponseBody
    @RequestMapping("/selectProjectByPname.do")
    public List<Project> selectALLProjectMsg(String pname) throws Throwable {
        return ps.selectProjectByPname(pname);
    }


}
