package com.example.swalayan.repository;

import com.example.swalayan.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepository;

    public void deleteEmployee(Long NIP){
        employeeRepository.deleteById(NIP);
    }

    public Employee save (Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findEmployee (Long NIP){
//        return new Employee();
        return employeeRepository.findById(NIP).get();
    }

    public List<Employee> findByEmpName(String EmpName){
        return null;
//        return employeeRepository.findByEmpName(EmpName);
    }

    public Iterable<Employee> findAll(){
        return employeeRepository.findAll();
    }




}
