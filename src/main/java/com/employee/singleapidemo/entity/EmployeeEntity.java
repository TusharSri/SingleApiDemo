package com.employee.singleapidemo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "employee")
public class EmployeeEntity {

    public EmployeeEntity() {
    }

    public EmployeeEntity(int employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    @Id
    public int employeeId;

    @Column(name = "employeeName", nullable = false)
    public String employeeName;

}

