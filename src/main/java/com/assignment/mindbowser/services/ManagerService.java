package com.assignment.mindbowser.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.mindbowser.entity.Manager;
import com.assignment.mindbowser.repo.IManagerRepository;
import com.assignment.mindbowser.services.interf.IManagerService;

@Service
public class ManagerService implements IManagerService {

	@Autowired
	private IManagerRepository managerRepo;

	@Override
	public Manager save(Manager manager) {
		return managerRepo.save(manager);
	}

	@Override
	public Manager update(Manager manager) {
		return managerRepo.save(manager);
	}

	@Override
	public void delete(Long managerId) {
		if (managerId != null) {
			Manager manager = managerRepo.getOne(managerId);
			managerRepo.delete(manager);

		}
	}

	@Override
	public Manager getManagerByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}