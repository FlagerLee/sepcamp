package com.seclass.sepcamp.models;

public class UserRegister {
    private String email;
    private String username;
    private String password;
    private String site_url;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSite_url() {
        return site_url;
    }
}
