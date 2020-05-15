package com.assignment.mindbowser.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.mindbowser.entity.Manager;

@Repository
public interface IManagerRepository extends JpaRepository<Manager, Long> {

	Manager findByUsername(String username);

}
