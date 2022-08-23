package com.security.demo.services;

import com.security.demo.entity.Manager;
import com.security.demo.repo.IManagerRepository;
import com.security.demo.services.interf.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Manager m =  managerRepo.findByUsername(email);
		System.out.println(m);
		return m;
		
	}

	@Override
	public Manager getManager(Long id) {
		return managerRepo.getOne(id);
	}

}
