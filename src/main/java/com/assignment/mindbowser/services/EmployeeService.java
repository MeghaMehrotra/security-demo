package com.assignment.mindbowser.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.mindbowser.entity.Employee;
import com.assignment.mindbowser.entity.Manager;
import com.assignment.mindbowser.repo.IEmployeeRepository;
import com.assignment.mindbowser.repo.IManagerRepository;
import com.assignment.mindbowser.services.interf.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeRepository employeeRepo;

	@Autowired
	private IManagerRepository managerRepo;

	@Override
	public Employee save(Employee emp) {
		return employeeRepo.save(emp);
	}

	@Override
	public Employee update(Employee emp) {
		return employeeRepo.save(emp);
	}

	@Override
	public void delete(Integer empId) {
		if (empId != null) {
			Employee emp = employeeRepo.getOne(empId);
			employeeRepo.delete(emp);

		}
	}

	@Override
	public List<Employee> getAllEmployees(Long managerId) {
		List<Employee> employees = new ArrayList<Employee>();
		if (managerId != null) {
			Manager manager = managerRepo.getOne(managerId);
			employees = manager.getEmployees();
		}
		return employees;
	}

}