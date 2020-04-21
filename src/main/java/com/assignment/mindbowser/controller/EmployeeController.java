package com.assignment.mindbowser.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.mindbowser.entity.Employee;
import com.assignment.mindbowser.services.interf.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@GetMapping("/{managerId}")
	public ResponseEntity<List<Employee>> getEmployee(@PathVariable("managerId") Long managerId) {
		System.out.println("==========="+managerId);
		List<Employee> employees = new ArrayList<Employee>();
		employees = employeeService.getAllEmployees(managerId);
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
//	@PutMapping
//	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
//		
//	}
	

}
