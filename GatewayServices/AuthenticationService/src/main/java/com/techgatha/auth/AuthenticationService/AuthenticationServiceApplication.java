package com.techgatha.auth.AuthenticationService;

import com.techgatha.auth.AuthenticationService.entity.UserInfo;
import com.techgatha.auth.AuthenticationService.repo.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private PasswordEncoder encoder;
	@PostConstruct
	public void initialize(){
		UserInfo u1 = new UserInfo(1,"Shalini","shalini@gmail.com",encoder.encode("sh123"));
		UserInfo u2 = new UserInfo(2,"prashant","prashant@gmail.com",encoder.encode("pr123"));
		userInfoRepository.saveAll(Arrays.asList(u1, u2));
		for(UserInfo info:userInfoRepository.findAll())
			System.out.println(info.getId()+ " "+info.getName());
	}
}
