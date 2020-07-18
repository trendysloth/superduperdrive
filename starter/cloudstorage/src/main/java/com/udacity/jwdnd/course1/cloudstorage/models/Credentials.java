package com.udacity.jwdnd.course1.cloudstorage.models;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;

import java.security.SecureRandom;
import java.util.Base64;

public class Credentials {

    private Integer credentialId;
    private String url;
    private String username;



    private String password;
    private Integer userId;
    private String encodedKey;
    private EncryptionService encryptionService;

    public EncryptionService getEncryptionService() {
        return encryptionService;
    }

    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    public String getEncodedKey() {
        return encodedKey;
    }

    public void setEncodedKey(String encodedKey) {
        this.encodedKey = encodedKey;
    }

    public Credentials() {

    }

    public String encryptPassword(String password) {
        System.out.println("Encryption key: " + this.encodedKey);
        System.out.println("Encryption Service: " + this.encryptionService);
        String encryptedPassword = this.encryptionService.encryptValue(password, this.encodedKey);
        return encryptedPassword;
    }
    public String decryptPassword(String password, EncryptionService encryptionService, String encodedKey) {
        System.out.println("Encryption Service: " + encryptionService);
        System.out.println("Decryption Key: " + encodedKey);
//        String decryptedPassword = this.encryptionService.encryptValue(password, this.encodedKey);
        return "haha";
    }


    public Credentials(String url, String username, String password, Integer userId,
                       EncryptionService encryptionService, String encodedKey) {
        this.encryptionService = encryptionService;
        this.encodedKey = encodedKey;
        this.url = url;
        this.username = username;
        this.password = this.encryptPassword(password);
        this.userId = userId;
    }

    public Credentials(Integer credentialId, String url, String username, String password, Integer userId,
                       EncryptionService encryptionService, String encodedKey) {
        this.encryptionService = encryptionService;
        this.encodedKey = encodedKey;
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.password = this.encryptPassword(password);
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


}