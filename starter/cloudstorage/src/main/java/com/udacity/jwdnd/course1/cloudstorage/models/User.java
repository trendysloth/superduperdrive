package com.udacity.jwdnd.course1.cloudstorage.models;

import lombok.Data;

@Data
public class User {
    private Integer userid;
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;

    public void setPassword(String encodedPW) {
        this.password = encodedPW;
    }

    public String getPassword() {
        return this.password;
    }
    public String getUsername() {
        return this.username;
    }
}