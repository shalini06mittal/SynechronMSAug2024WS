package com.web.SpringBootRestDemo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.SpringBootRestDemo.entity.Employee;
import com.web.SpringBootRestDemo.repo.EmployeeRepo;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeServiceRepo {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	public List<Employee> getEmployees() {
		return this.employeeRepo.findAll();
	}
	public Employee getEmployeeById(int id) {
		return 
			employeeRepo.findById(id)
			.orElseThrow(()->new EntityNotFoundException("Employee not found"));
	}
	public List<Employee> getEmployeesByNameStarts(String startsWith) {
		
		
		return this.employeeRepo.findByEnameStartingWith(startsWith);
	}
	public Employee addEmployee(Employee emp) {
		
		if(this.employeeRepo.existsById(emp.getEid()))
				throw new EntityExistsException("Employee exists cannot insert");
			
		return this.employeeRepo.save(emp);
	}
	public Employee updateEmployee(Employee emp) {
		if(! this.employeeRepo.existsById(emp.getEid()))
			throw new  EntityNotFoundException("Employee not found");
		
		return this.employeeRepo.save(emp);
		
	}
	public boolean deleteEmployee(int id) {
		this.employeeRepo.deleteById(id);
		if(this.employeeRepo.existsById(id))
			throw new RuntimeException("Could not delete");
		return true;
	}
	
}
