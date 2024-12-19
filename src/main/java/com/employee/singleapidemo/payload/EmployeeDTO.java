package com.employee.singleapidemo.payload;

import jakarta.validation.constraints.*;

public record EmployeeDTO(
        @NotNull int employeeId,
        @NotBlank @Size(min = 5, message = "Name should contain at least 5 characters.") String employeeName,
        @NotBlank @Size(min = 3, message = "Username should contain at least 3 characters.") String employeeUsername,
        @Email(message = "Enter a valid email id") String employeeEmail,
        @NotBlank @Size(min = 4, message = "Password should contain at least 4 characters.") String employeePassword,
        @NotBlank @Pattern(regexp = "^(admin|user)$", message = "Role should be either admin or user") String employeeRole
) {
}
