package com.employee.singleapidemo.repository;

import com.employee.singleapidemo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    Optional<EmployeeEntity> findByEmployeeEmail(String employeeEmail);
    Optional<EmployeeEntity> findByEmployeeUserName(String employeeName);
    Optional<EmployeeEntity> findByEmployeeUserNameOrEmployeeEmail(String employeeName, String employeeEmail);

    Boolean existsByEmployeeUserName(String employeeName);
    Boolean existsByEmployeeEmail(String employeeEmail);
    Boolean existsByEmployeeId(int employeeId);
}
