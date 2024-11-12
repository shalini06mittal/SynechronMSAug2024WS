package com.techgatha.auth.AuthenticationService.config;

import com.techgatha.auth.AuthenticationService.entity.UserInfo;
import com.techgatha.auth.AuthenticationService.repo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserInfoDetailsDervice implements UserDetailsService{

    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> credential = repository.findByName(username);
        return credential.map(UserInfoDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found : "+username));
    }
}
