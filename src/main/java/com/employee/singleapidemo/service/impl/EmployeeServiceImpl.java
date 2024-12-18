package com.employee.singleapidemo.service.impl;

import com.employee.singleapidemo.entity.EmployeeEntity;
import com.employee.singleapidemo.payload.EmployeeDTO;
import com.employee.singleapidemo.repository.EmployeeRepository;
import com.employee.singleapidemo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

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
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow();
        return mapToDTO(employeeEntity);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees(EmployeeDTO employeeDto) {
        return null;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDto) {
        return null;
    }

    @Override
    public EmployeeDTO deleteEmployee(int employeeId) {
        return null;
    }

    public EmployeeDTO mapToDTO(EmployeeEntity employeeEntity) {
        return new EmployeeDTO(employeeEntity.employeeId, employeeEntity.employeeName);
    }

    public EmployeeEntity mapToEntity(EmployeeDTO employeeDto) {
        return new EmployeeEntity(employeeDto.employeeId(), employeeDto.employeeName());
    }
}
