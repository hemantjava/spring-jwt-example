package com.hemant.springjwtexample.controller;

import com.hemant.springjwtexample.model.AuthenticationRequest;
import com.hemant.springjwtexample.model.AuthenticationResponse;
import com.hemant.springjwtexample.service.MyUserDetailsService;
import com.hemant.springjwtexample.utils.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class HelloController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    //localhost:8181/hello
    //localhost:8181/greeting
    @GetMapping({"/hello", "/greeting"})
    public String getGreeting() {
        log.info("getGreeting() Executed");
        return "Hello How are you";
    }

    //localhost:8181/authenticate
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authRequest(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),
                    request.getPassword()));
        } catch (Exception exception) {
            throw new BadCredentialsException("Bad credential ...", exception);
        }
        final String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
