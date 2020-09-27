package com.scd.erp.Vo.Person;

import com.scd.erp.user.Project;
import com.scd.erp.user.ProjectRole;
import com.scd.erp.user.Task;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Table(name = "erp_person_user")
public class User implements Serializable {
    @Id
    @Column(name = "UserID")
    private Integer userid;

    private String username;

    private String password;

    @Column(name = "presonID")
    private Integer presonid;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("userid=").append(userid);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", presonid=").append(presonid);
        sb.append(", person=").append(person);
        sb.append(", projectRole=").append(projectRole);
        sb.append(", projects=").append(projects);
        sb.append(", task=").append(task);
        sb.append('}');
        return sb.toString();
    }

    private Person person;

    private ProjectRole projectRole;
    private List<Project> projects;

    private List<Task> task;

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public ProjectRole getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(ProjectRole projectRole) {
        this.projectRole = projectRole;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * @return UserID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return presonID
     */
    public Integer getPresonid() {
        return presonid;
    }

    /**
     * @param presonid
     */
    public void setPresonid(Integer presonid) {
        this.presonid = presonid;
    }
}