package com.abc.crm.dao;

import com.abc.crm.entity.User;
import com.abc.crm.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDao {

    private final UserRepo userRepo;

    @PostConstruct
    public void init(){
        List<User> userList = List.of(
                User.builder().username("superuser")
                        .password("123")
                        .role("SUPERUSER").build(),
                User.builder().username("manager")
                        .password("123")
                        .role("MANAGER").build(),
                User.builder().username("operator")
                        .password("123")
                        .role("OPERATOR").build());

        userRepo.saveAll(userList);
    }

    public Optional<User> getByUsername(String username) {
        return userRepo.findByUsername(username);
    }



}
