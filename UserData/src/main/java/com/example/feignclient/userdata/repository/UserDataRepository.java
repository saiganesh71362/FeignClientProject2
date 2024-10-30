package com.example.feignclient.userdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feignclient.userdata.entity.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
	
	

}
