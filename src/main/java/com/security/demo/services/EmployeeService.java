package com.security.demo.services;

import java.util.ArrayList;
import java.util.List;

import com.security.demo.entity.Employee;
import com.security.demo.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.demo.repo.IEmployeeRepository;
import com.security.demo.repo.IManagerRepository;
import com.security.demo.services.interf.IEmployeeService;

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
	public void delete(Long empId) {
		if (empId != null) {
			Employee emp = employeeRepo.getOne(empId);
			employeeRepo.delete(emp);

		}
	}

	@Override
	public List<Employee> getAllEmployees(Long managerId) {
		List<Employee> employees = new ArrayList<>();
		if (managerId != null) {
			Manager manager = managerRepo.getOne(managerId);
			for (Employee emp : manager.getEmployees()) {
				if (emp.getIsDeleted().equals(false)) {
					employees.add(emp);
				}
			}
		}
		return employees;
	}

	@Override
	public Employee getEmployee(Long employeeId) {
		Employee emp = null;
		if (employeeId != null) {
			emp = employeeRepo.getOne(employeeId);
		}
		return emp;
	}

}
