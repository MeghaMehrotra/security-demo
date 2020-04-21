package com.assignment.mindbowser.services.interf;

import java.util.List;

import com.assignment.mindbowser.entity.Employee;

public interface IEmployeeService {

	Employee save(Employee emp);

	Employee update(Employee emp);

	void delete(Integer empId);
	
	List<Employee> getAllEmployees(Long managerId);
}
