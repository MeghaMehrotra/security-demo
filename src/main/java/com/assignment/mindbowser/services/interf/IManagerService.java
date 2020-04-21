package com.assignment.mindbowser.services.interf;

import com.assignment.mindbowser.entity.Manager;

public interface IManagerService {
	
     Manager save(Manager manager);
     
     Manager update(Manager manager);
     
     void delete(Long managerId);

	Manager getManagerByEmail(String email);
}
