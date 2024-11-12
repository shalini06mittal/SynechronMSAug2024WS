package com.web.SpringBootRestDemo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.SpringBootRestDemo.entity.Employee;

import jakarta.persistence.criteria.CriteriaBuilder;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	
	// CriteriaBuilder
	public List<Employee> findByEnameStartingWith(String startsWith);
	
}
