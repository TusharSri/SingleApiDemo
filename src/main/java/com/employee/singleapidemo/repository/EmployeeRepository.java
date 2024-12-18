package com.employee.singleapidemo.repository;

import com.employee.singleapidemo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

}
