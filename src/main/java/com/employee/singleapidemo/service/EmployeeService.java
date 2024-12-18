package com.employee.singleapidemo.service;

import com.employee.singleapidemo.payload.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDto);

    EmployeeDTO getEmployee(int employeeId);

    List<EmployeeDTO> getAllEmployees(EmployeeDTO employeeDto);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDto);

    EmployeeDTO deleteEmployee(int employeeId);
}
