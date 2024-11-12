package com.techgatha.auth.AuthenticationService.service;

import com.techgatha.auth.AuthenticationService.entity.UserInfo;
import com.techgatha.auth.AuthenticationService.repo.UserInfoRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserInfo info){
        info.setPassword(encoder.encode(info.getPassword()));
        userInfoRepository.save(info);
        return "User Info added to the system";
    }
    public UserInfo getUserInfo(int id){
        return userInfoRepository.findById(id).get();
    }
    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
