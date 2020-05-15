package com.assignment.mindbowser.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.mindbowser.dto.MessageDTO;
import com.assignment.mindbowser.entity.Employee;
import com.assignment.mindbowser.entity.Manager;
import com.assignment.mindbowser.services.interf.IEmployeeService;
import com.assignment.mindbowser.services.interf.IManagerService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IManagerService managerService;

	@GetMapping("/manager/{managerId}")
	public ResponseEntity<List<Employee>> getEmployeesByManager(@PathVariable("managerId") Long managerId) {
		 List<Employee> employees = employeeService.getAllEmployees(managerId);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@GetMapping("/{employeeId}/manager/{managerId}")
	public ResponseEntity<MessageDTO<Employee>> getEmployee(@PathVariable("employeeId") Long employeeId,
			@PathVariable Long managerId) {
		Employee employee = null;
		if (employeeId != null) {
			employee = employeeService.getEmployee(employeeId);

		}
		return new ResponseEntity<>(
				new MessageDTO<>("Employee save success!!", employee, true), HttpStatus.OK);
	}

	@PostMapping("/{email}")
	public ResponseEntity<MessageDTO<Employee>> saveEmployee(@RequestBody @Valid Employee employee,
			@PathVariable("email") String email) {
		Employee emp = null;
		if (employee != null) {
			emp = employeeService.save(employee);
			Manager manager = managerService.getManagerByEmail(email);
			manager.getEmployees().add(emp);
			managerService.save(manager);
		}
		return new ResponseEntity<>(new MessageDTO<>("Employee save success!!", emp, true),
				HttpStatus.CREATED);

	}

	@PostMapping("/update")
	public ResponseEntity<MessageDTO<Employee>> updateEmployee(@RequestBody Employee employee) {
		Employee emp = null;
		if (employee != null) {
			emp = employeeService.save(employee);
		}
		return new ResponseEntity<>(
				new MessageDTO<>("Employee update success!!", emp, true), HttpStatus.OK);
	}

	@GetMapping("/delete/{employeeId}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		Employee employee = null;
		if (employeeId != null) {
			employee = employeeService.getEmployee(employeeId);
			employee.setIsDeleted(true);
			employeeService.save(employee);
			return new ResponseEntity<>(new MessageDTO<>("Employee delete success!!", null, true),
					HttpStatus.OK);
		}

		return new ResponseEntity<>(new MessageDTO<>("Employee delete success!!", null, false),
				HttpStatus.OK);
	}

}
