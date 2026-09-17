package com.example.backend_java.Controller;


import com.example.backend_java.DTO.LoginDTO;
import com.example.backend_java.Service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO data){

            String token = loginService.Login(data.matricula(), data.senha());
            if (token != null) return ResponseEntity.status(HttpStatus.OK).body(token);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
}