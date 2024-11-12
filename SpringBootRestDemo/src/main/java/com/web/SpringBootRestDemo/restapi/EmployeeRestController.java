package com.web.SpringBootRestDemo.restapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.SpringBootRestDemo.entity.Employee;
import com.web.SpringBootRestDemo.service.EmployeeService;
import com.web.SpringBootRestDemo.service.EmployeeServiceRepo;
import com.web.SpringBootRestDemo.utility.AppConstants;
import com.web.SpringBootRestDemo.utility.Status;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;

@RestController

@RequestMapping("/employees")
public class EmployeeRestController {

	//@Autowired 
	private EmployeeServiceRepo employeeService;

	public EmployeeRestController(EmployeeServiceRepo employeeService) {
		System.out.println("emp constructor");
		this.employeeService = employeeService;
	}
	//	@GetMapping
	//	public List<Employee> getAllEmployees(){
	//		return employeeService.getEmployees();
	//	}
	@GetMapping
	@ApiResponse(description = "API endpoints for employee operations")
	public List<Employee> getEmployees(
			@RequestParam(defaultValue = "S") String nameStartsWith){
//		if(nameStartsWith != null)
//			return employeeService.getEmployeesByNameStarts(nameStartsWith);
//		return employeeService.getEmployees();
		if(nameStartsWith.equals("all"))
			return employeeService.getEmployees();
		return employeeService.getEmployeesByNameStarts(nameStartsWith);
	}
	
	class ResponseDTO{
		String status;
		String message;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable() Integer id){
		Map<String, Object> map = new HashMap();
		//try {
			Employee emp = employeeService.getEmployeeById(id);
			map.put("employee", emp);
			map.put(AppConstants.STATUS, Status.SUCCESS);
			return  ResponseEntity.ok().body(map);
//		}
//		catch(EntityNotFoundException e) {
//			//return  ResponseEntity.badRequest().body(e.getMessage());
//			map.put(AppConstants.STATUS, Status.FAILURE);
//			map.put(AppConstants.ERRORMESSAGE, e.getMessage());
//			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
//		}
	}
	@PostMapping
	public Employee addEmployee(@RequestBody Employee emp){
		return employeeService.addEmployee(emp);
	}
	@DeleteMapping("/{id}")
	public boolean deleteEmployeeBYId(@PathVariable Integer id){
		return employeeService.deleteEmployee(id);
	}
	@PutMapping
	public Employee updateEmployee(@RequestBody Employee emp){
		return employeeService.updateEmployee(emp);
	}
	@PatchMapping
	public Employee updatePatchOfEmployee(@RequestBody Employee emp){

		/*
		 * update emp set cnames
		 */
		return employeeService.updateEmployee(emp);
	}
}

