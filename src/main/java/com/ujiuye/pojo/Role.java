package com.ujiuye.pojo;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer roleid;
    private String rolename;
    private String roledis;
    private Integer status;

    public Role(Integer roleid, String rolename, String roledis, Integer status) {
        this.roleid = roleid;
        this.rolename = rolename;
        this.roledis = roledis;
        this.status = status;
    }

    public Role() {
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledis() {
        return roledis;
    }

    public void setRoledis(String roledis) {
        this.roledis = roledis;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                ", roledis='" + roledis + '\'' +
                ", status=" + status +
                '}';
    }
}
