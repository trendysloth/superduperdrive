package com.udacity.jwdnd.course1.cloudstorage.models;

public class Credentials {
    private Integer credentialId;
    private String url;
    private String username;
    private String password;
    private Integer userId;
    private String key;

    public Credentials() {
    }

    public Credentials(String url, String username, String password, Integer userId, String key) {
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public Credentials(Integer credentialId, String url, String username, String password, Integer userId, String key) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }
}