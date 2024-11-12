package com.techgatha.auth.AuthenticationService.controller;

import com.techgatha.auth.AuthenticationService.dto.AuthenticationRequest;
import com.techgatha.auth.AuthenticationService.entity.UserInfo;
import com.techgatha.auth.AuthenticationService.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * {
     *     "name":"prashant",
     *     "password":"456",
     *     "email":"prashant@gmail.com"
     * }
     * {
     *     "name":"shalini",
     *     "password":"123",
     *     "email":"shalini@gmail.com"
     * }
     * @param userInfo
     * @return
     */
    @PostMapping("/register")
    public String registerUser(@RequestBody UserInfo userInfo){
        return authService.saveUser(userInfo);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthenticationRequest authRequest) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return authService.generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }

    }
    @GetMapping("/user/{id}")
    public UserInfo fetchUserById(@PathVariable  int id){
        return authService.getUserInfo(id);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token){
        System.out.println(token);
        try {
            authService.validateToken(token);
            return "Token is valid";
        }catch (Exception e){
            return "Token not valid";
        }

    }

}
