package com.employee.singleapidemo.service.impl;


import com.employee.singleapidemo.entity.EmployeeEntity;
import com.employee.singleapidemo.exception.EmployeeAlreadyExistException;
import com.employee.singleapidemo.payload.EmployeeDTO;
import com.employee.singleapidemo.payload.LoginDTO;
import com.employee.singleapidemo.repository.EmployeeRepository;
import com.employee.singleapidemo.security.JwtTokenProvider;
import com.employee.singleapidemo.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager, EmployeeRepository employeeRepository,PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmployeeUserNameOrEmployeeEmail(), loginDto.getEmployeePassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(EmployeeDTO employeeDTO) {
        if(employeeRepository.existsByEmployeeId(employeeDTO.employeeId())){
            throw new EmployeeAlreadyExistException("Employee ID is already exists! ");
        }

        if(employeeRepository.existsByEmployeeUserName(employeeDTO.employeeUsername())){
            throw new EmployeeAlreadyExistException("Username is already exists! ");
        }

        if(employeeRepository.existsByEmployeeEmail(employeeDTO.employeeEmail())){
            throw new EmployeeAlreadyExistException("Email is already exists! ");
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(employeeDTO.employeeId());
        employeeEntity.setEmployeeName(employeeDTO.employeeName());
        employeeEntity.setEmployeeUserName(employeeDTO.employeeUsername());
        employeeEntity.setEmployeeEmail(employeeDTO.employeeEmail());
        employeeEntity.setEmployeePassword(passwordEncoder.encode(employeeDTO.employeePassword()));

        if ("admin".equalsIgnoreCase(employeeDTO.employeeRole())) {
            employeeEntity.setEmployeeRole("ROLE_ADMIN");
        } else {
            employeeEntity.setEmployeeRole("ROLE_USER");
        }

        employeeRepository.save(employeeEntity);

        return "User Registered successfully!.";
    }
}
