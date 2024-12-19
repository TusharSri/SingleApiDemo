package com.employee.singleapidemo.controller;

import com.employee.singleapidemo.payload.EmployeeDTO;
import com.employee.singleapidemo.payload.LoginDTO;
import com.employee.singleapidemo.security.JWTAuthResponse;
import com.employee.singleapidemo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //Build login Rest API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDto){
        String token = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    //Build register Api
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody EmployeeDTO employeeDTO){
        String response = authService.register(employeeDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}