package com.employee.singleapidemo.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeeDTO(
        @NotNull int employeeId,
        @NotBlank @Size(min = 5, message = "Name should contain at least 5 characters.") String employeeName
) {
}
