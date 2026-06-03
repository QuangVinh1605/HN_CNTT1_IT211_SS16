package org.example.session16bai2.controller;


import org.example.session16bai2.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @GetMapping
    public List<Employee> getEmployees() {
        return List.of(
                new Employee(1L, "Nguyen Van A", "Developer"),
                new Employee(2L, "Tran Thi B", "Tester"),
                new Employee(3L, "Le Van C", "Manager")
        );
    }
}