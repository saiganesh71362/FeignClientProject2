package com.example.feignclient.sim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feignclient.sim.entity.Sim;

public interface SimRepository extends JpaRepository<Sim, Long> {
	
	 List<Sim> findByMobileId(Long mobileId);

}
