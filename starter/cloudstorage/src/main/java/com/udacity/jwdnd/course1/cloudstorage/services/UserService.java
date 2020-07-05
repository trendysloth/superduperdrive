package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper UserMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UserMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        return user;
    }


    public User register(User user) throws Exception {
        String encodedPW = user.getPassword();
        System.out.println(encodedPW);
        user.setPassword(encodedPW);
        try {
            UserMapper.insertUser(user);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }


}
