package com.assignment.mindbowser.services.interf;

import java.util.List;

import com.assignment.mindbowser.entity.Employee;

public interface IEmployeeService {

	Employee save(Employee emp);

	Employee update(Employee emp);

	void delete(Long employeeId);
	
	List<Employee> getAllEmployees(Long managerId);

	Employee getEmployee(Long employeeId);
}
