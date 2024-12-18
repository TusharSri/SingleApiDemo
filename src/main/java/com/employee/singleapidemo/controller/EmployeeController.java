package com.employee.singleapidemo.controller;

import com.employee.singleapidemo.payload.EmployeeDTO;
import com.employee.singleapidemo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody @Valid EmployeeDTO employeeDto) {
        return new ResponseEntity<>(employeeService.addEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<String> updateEmployeeDetails(@RequestBody @Valid EmployeeDTO employeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDto), HttpStatus.OK);
    }
}
