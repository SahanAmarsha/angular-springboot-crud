package com.iamsahan.employeemanagerbackend.service;

import com.iamsahan.employeemanagerbackend.exception.UserNotFoundException;
import com.iamsahan.employeemanagerbackend.model.Employee;
import com.iamsahan.employeemanagerbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService
{
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService( EmployeeRepository employeeRepository )
    {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode( UUID.randomUUID().toString());
        return employeeRepository.save( employee );
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save( employee );
    }

    public Employee findEmployeeById(Long id) throws UserNotFoundException
    {
        return employeeRepository.findEmployeeById( id )
                                 .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteEmployeeById(id);
    }
}
