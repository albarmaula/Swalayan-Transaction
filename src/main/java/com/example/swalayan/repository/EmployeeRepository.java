package com.example.swalayan.repository;

import com.example.swalayan.model.Employee;
import org.springframework.data.repository.CrudRepository;
public interface EmployeeRepository extends CrudRepository <Employee, Long> {
//    List<Employee> findByEmpName(String empName);

}
