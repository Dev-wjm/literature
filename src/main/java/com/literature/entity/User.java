package com.literature.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "sys_users")
public class User implements Serializable {
    @Id
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(
            name = "sys_users_roles", // 指定中间表名
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}, // 这里的id是sys_user表的id
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")} // 这里的id是sys_role表的id
    )
    private Set<Role> roleIds;

    @Column(name = "locked")
    private Boolean locked = Boolean.FALSE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<Role> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Role> roleIds) {
        this.roleIds = roleIds;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
