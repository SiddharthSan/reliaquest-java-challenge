package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImplementation;
import com.challenge.api.model.CreateEmployeeRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployeeByUuid(UUID uuid) {
        return employees.stream().filter(employee -> employee.getUuid().equals(uuid))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Employee uuid not found: " + uuid));
    }

    public Employee createEmployee(CreateEmployeeRequest request) {
        EmployeeImplementation e = new EmployeeImplementation();
        e.setUuid(UUID.randomUUID());
        e.setFirstName(request.getFirstName());
        e.setLastName(request.getLastName());
        e.setFullName(request.getFirstName() + " " + request.getLastName());
        e.setSalary(request.getSalary());
        e.setAge(request.getAge());
        e.setJobTitle(request.getJobTitle());
        e.setEmail(request.getEmail());
        e.setContractHireDate(Instant.now());
        e.setContractTerminationDate(null);

        employees.add(e);
        return e;
    }
}