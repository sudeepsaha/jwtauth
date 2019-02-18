package com.ece.opencourse.model;

public class JwtToken {

    private String userName;
    private String[] roles;

    public JwtToken() {
        super();
    }

    public JwtToken(String userName, String[] roles) {
        this.userName = userName;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

}
