package com.scogen.bank.controller;

import com.scogen.bank.Model.BearerToken;
import com.scogen.bank.entity.AuthRequest;
import com.scogen.bank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to ScoGen !!";
    }

    @PostMapping("/authenticate")
    public BearerToken generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        BearerToken bearerToken = new BearerToken();
        bearerToken.setToken(jwtUtil.generateToken(authRequest.getUserName()));
        return bearerToken;
    }
}
