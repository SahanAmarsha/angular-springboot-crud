package com.iamsahan.employeemanagerbackend.api;

import com.iamsahan.employeemanagerbackend.model.Employee;
import com.iamsahan.employeemanagerbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController
{
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController( EmployeeService employeeService )
    {
        this.employeeService = employeeService;
    }

    @GetMapping ("/all")// get all employees
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = employeeService.findAllEmployees();
        return new ResponseEntity<>( employeeList, HttpStatus.OK );
    }

    @GetMapping("/find/{id}") // get all employees
    public ResponseEntity<Employee> getEmployeeById( @PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK );
    }

    @PostMapping("/add") // add new employee
    public ResponseEntity<Employee> addEmployee( @RequestBody Employee employee ) {
        Employee newEmployee = employeeService.addEmployee( employee );
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED );
    }

    @PutMapping("/update") // update employee
    public ResponseEntity<Employee> updateEmployee( @RequestBody Employee employee ) {
        Employee updatedEmployee = employeeService.updateEmployee( employee );
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK );
    }

    @DeleteMapping("/delete/{id}") // delete employee
    public ResponseEntity<Long> deleteEmployee( @PathVariable("id") Long id) {
        employeeService.deleteEmployee( id );
        return new ResponseEntity<>(id, HttpStatus.OK );
    }
}
