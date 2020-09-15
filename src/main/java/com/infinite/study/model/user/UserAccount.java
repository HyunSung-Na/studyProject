package com.infinite.study.model.user;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class UserAccount extends User {

    private com.infinite.study.model.user.User user;

    public UserAccount(com.infinite.study.model.user.User user) {
        super(user.getNickname(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.user = user;
    }


}
