package com.literature.vo;

import java.util.List;

public class AuthorityVo {
    private String id;
    private String rolename;
    private List authority;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List getAuthority() {
        return authority;
    }

    public void setAuthority(List authority) {
        this.authority = authority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
