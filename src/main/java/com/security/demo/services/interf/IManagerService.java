package com.security.demo.services.interf;

import com.security.demo.entity.Manager;

public interface IManagerService {
	
     Manager save(Manager manager);
     
     Manager update(Manager manager);
     
     void delete(Long managerId);

	Manager getManagerByEmail(String email);
	
	Manager getManager(Long id);
}
