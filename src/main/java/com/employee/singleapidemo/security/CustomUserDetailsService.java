package com.employee.singleapidemo.security;

import com.employee.singleapidemo.entity.EmployeeEntity;
import com.employee.singleapidemo.repository.EmployeeRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private EmployeeRepository employeeRepository;

    public CustomUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String employeeUsernameOrEmail) throws UsernameNotFoundException {
        EmployeeEntity employeeEntity = employeeRepository.findByEmployeeUserNameOrEmployeeEmail(employeeUsernameOrEmail, employeeUsernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username or email "+ employeeUsernameOrEmail));

        GrantedAuthority authority = new SimpleGrantedAuthority(employeeEntity.getEmployeeRole());

        return new org.springframework.security.core.userdetails.User(employeeEntity.getEmployeeEmail(), employeeEntity.getEmployeePassword(), Collections.singleton(authority));
    }
}
