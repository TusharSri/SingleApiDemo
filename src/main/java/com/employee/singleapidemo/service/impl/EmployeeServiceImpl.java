package com.employee.singleapidemo.service.impl;

import com.employee.singleapidemo.entity.EmployeeEntity;
import com.employee.singleapidemo.exception.EmployeeNotFoundException;
import com.employee.singleapidemo.payload.EmployeeDTO;
import com.employee.singleapidemo.repository.EmployeeRepository;
import com.employee.singleapidemo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private static final String EMPLOYEE_NOT_FOUND = "Employee not available in Database";

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String addEmployee(EmployeeDTO employeeDto) {
        EmployeeEntity employeeEntity = mapToEntity(employeeDto);
        employeeRepository.save(employeeEntity);
        return "Employee saved successfully to Database";
    }

    @Override
    public EmployeeDTO getEmployee(int employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(HttpStatus.NOT_FOUND, EMPLOYEE_NOT_FOUND));
        return mapToDTO(employeeEntity);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String updateEmployee(EmployeeDTO employeeDto) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeDto.employeeId())
                .orElseThrow(() -> new EmployeeNotFoundException(HttpStatus.NOT_FOUND, EMPLOYEE_NOT_FOUND));
        employeeEntity.employeeName = employeeDto.employeeName();
        employeeRepository.save(employeeEntity);
        return "Employ name updated successfully";
    }

    @Override
    public String deleteEmployee(int employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(HttpStatus.NOT_FOUND, EMPLOYEE_NOT_FOUND));
        employeeRepository.delete(employeeEntity);
        return "Employee deleted successfully";
    }

    public EmployeeDTO mapToDTO(EmployeeEntity employeeEntity) {
        return new EmployeeDTO(employeeEntity.getEmployeeId(),
                employeeEntity.getEmployeeName(),
                employeeEntity.getEmployeeUserName(),
                employeeEntity.getEmployeeEmail(),
                employeeEntity.getEmployeePassword(),
                employeeEntity.getEmployeeRole());
    }

    public EmployeeEntity mapToEntity(EmployeeDTO employeeDto) {
        return new EmployeeEntity(employeeDto.employeeId()
                , employeeDto.employeeName()
                , employeeDto.employeeUsername()
                , employeeDto.employeeEmail()
                , employeeDto.employeePassword()
                , employeeDto.employeeRole());
    }
}
