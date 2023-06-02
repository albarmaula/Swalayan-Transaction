package com.example.swalayan.controller;

import com.example.swalayan.model.Employee;
import com.example.swalayan.repository.EmployeeRepository;
import com.example.swalayan.repository.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public Iterable<Employee> findAllEmployee(){
        return employeeService.findAll();
    }

    @GetMapping("/{nip}")
    public Employee findEmployee(@PathVariable("nip") Long nip){
        return employeeService.findEmployee(nip);
    }

    @PostMapping
    public Employee addEmploye(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/{nip}")
    public void deleteEmployee(@PathVariable("nip")Long NIP){
        employeeService.deleteEmployee(NIP);
    }
}
