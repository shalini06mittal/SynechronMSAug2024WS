package com.web.SpringBootRestDemo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.SpringBootRestDemo.entity.Employee;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

	private List<Employee> employees;
	
	public EmployeeService() {
		employees = new ArrayList<>();
		
		employees.add(new Employee(1, "Shalini", "s@g.c", "1212121212",0, "sha"));
		employees.add(new Employee(2, "Meena", "m@g.c", "8787878787", 0,"me"));
		//employees.add(new Employee(3, "Alok", "a@g.c", "1212121212", "al",0));
		//employees.add(new Employee(4, "Aman", "am@g.c", "1212121212", "am",""));
	}
	
	public List<Employee> getEmployees() {
		return this.employees;
	}
	public Employee getEmployeeById(int id) {
		for(Employee e:employees) {
			if(e.getEid()==id)
				return e;
		}
		
		throw new EntityNotFoundException("Employee not found");
	}
	public List<Employee> getEmployeesByNameStarts(String startsWith) {
		List<Employee> namesStartsWithList = new ArrayList<>();
		for(Employee e:employees) {
			if(e.getEname().startsWith(startsWith))
				namesStartsWithList.add(e);
		}
		
		return namesStartsWithList;
	}
	public Employee addEmployee(Employee emp) {
		int size = employees.size();
		for(int i=0;i<size;i++) {
			if(employees.get(i).getEid()==emp.getEid()) {
				throw new EntityExistsException("Employee exists cannot insert");
			}
		}
		int id = employees.get(size-1).getEid() + 1;
		emp.setEid(id);
		employees.add(emp);
		return emp;
	}
	public Employee updateEmployee(Employee emp) {
		int size = employees.size();
		for(int i=0;i<size;i++) {
			if(employees.get(i).getEid()==emp.getEid()) {
				employees.set(i, emp);
				return emp;
			}
		}
		throw new  EntityNotFoundException("Employee not found");
	}
	public boolean deleteEmployee(int id) {
		int size = employees.size();
		for(int i=0;i<size;i++) {
			if(employees.get(i).getEid()==id) {
				employees.remove(employees.get(i));
				return true;
			}
		}
		throw new  EntityNotFoundException("Employee not found");
	}
	
}
