package com.employee.singleapidemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    public int employeeId;

    @Column(name = "employeeName", nullable = false)
    public String employeeName;

    @Column(name = "employeeUserName", nullable = false, unique = true)
    private String employeeUserName;

    @Column(name = "employeeEmail", nullable = false, unique = true)
    private String employeeEmail;

    @Column(name = "employeePassword", nullable = false)
    private String employeePassword;

    @Column(name = "employeeRole", nullable = false)
    private String employeeRole;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "employee_roles",
//            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "employeeId"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId"))
//    private Set<Role> roles;


}

