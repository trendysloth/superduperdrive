package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public Credentials uploadCredential(String url,
                                        String username,
                                        String password,
                                        Integer userId) throws IOException {
        Credentials newCredential = new Credentials(
            url,
            username,
            password,
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
        Credentials newCredential = new Credentials(
            credentialId,
            credentialUrl,
            credentialUsername,
            credentialPassword,
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
