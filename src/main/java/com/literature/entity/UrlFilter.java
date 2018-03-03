package com.literature.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_url")
public class UrlFilter implements Serializable {

    @Id
    private String id;

    @Column
    private String name; //url名称/描述
    @Column
    private String url; //地址
    @Column
    private String roles; //所需要的角色，可省略
    @Column
    private String permissions; //所需要的权限，可省略

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
