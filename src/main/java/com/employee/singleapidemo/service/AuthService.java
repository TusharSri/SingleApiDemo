package com.employee.singleapidemo.service;

import com.employee.singleapidemo.payload.EmployeeDTO;
import com.employee.singleapidemo.payload.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDto);

    String register(EmployeeDTO employeeDTO);
}
