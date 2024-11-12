package com.techgatha.auth.AuthenticationService.repo;

import com.techgatha.auth.AuthenticationService.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> findByName(String name);
}
