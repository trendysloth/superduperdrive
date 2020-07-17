package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;
    private String encodedKey;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        this.encodedKey = Base64.getEncoder().encodeToString(key);
    }

    public Credentials uploadCredential(String url,
                                        String username,
                                        String password,
                                        Integer userId) throws IOException {
        String encryptedPassword = encryptionService.encryptValue(password, this.encodedKey);
        Credentials newCredential = new Credentials(
            url,
            username,
            encryptedPassword,
            userId
        );
        try {
            credentialMapper.save(newCredential);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCredential;
    }

    public Credentials updateCredential(Integer credentialId,
                                        String credentialUrl,
                                        String credentialUsername,
                                        String credentialPassword,
                                        Integer userId) throws IOException {
        String encryptedPassword = encryptionService.encryptValue(credentialPassword, this.encodedKey);
        Credentials newCredential = new Credentials(
            credentialId,
            credentialUrl,
            credentialUsername,
            encryptedPassword,
            userId
        );
        try {
            credentialMapper.update(newCredential);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newCredential;
    }

    public List<Credentials> getAllCredentials(Integer userId) {
        return credentialMapper.findCredentialsByUserId(userId);
    }

    public void deleteCredential(Integer credentialId) throws IOException {
        try {
            credentialMapper.deleteById(credentialId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
