package com.assignment.mindbowser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.mindbowser.dto.MessageDTO;
import com.assignment.mindbowser.entity.Manager;
import com.assignment.mindbowser.exception.custom.MindbowserMessageException;
import com.assignment.mindbowser.services.interf.IManagerService;

@RestController
public class ManagerController {

	@Autowired
	IManagerService managerService;

	@PostMapping("/signup")
	public ResponseEntity<MessageDTO<Manager>> signup(@RequestBody Manager manager) throws MindbowserMessageException {
		if (manager != null && !manager.getEmail().isEmpty()) {
			if (!checkEmailExists(manager.getEmail())) {
				manager = managerService.save(manager);
				return new ResponseEntity<MessageDTO<Manager>>(
						new MessageDTO<Manager>(" Manager registration success!!", manager, true), HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<MessageDTO<Manager>>(
				new MessageDTO<Manager>(" Enter valid manager details!!", null, true), HttpStatus.OK);
	}

	public boolean checkEmailExists(String email) throws MindbowserMessageException {
		if (!email.isEmpty()) {
			Manager manager;
			manager = managerService.getManagerByEmail(email);
			if (manager != null)
				return true;
			else
				return false;
		}
		throw new MindbowserMessageException("Empty Email.Please enter email", HttpStatus.OK);
	}

}
