package com.hemant.springjwtexample.service;

import com.hemant.springjwtexample.entity.repository.UserRepository;
import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final com.hemant.springjwtexample.entity.User user = userRepository
            .findByUserName(username);
        log.info(user);
        //username,  password, Collection(authorities)
        return new User(user.getUserName(), user.getPassWord(), Arrays.asList());
    }
}
