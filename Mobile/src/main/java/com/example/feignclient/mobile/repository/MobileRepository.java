package com.example.feignclient.mobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.feignclient.mobile.entity.Mobile;

public interface MobileRepository extends JpaRepository<Mobile, Long> {

//	findByMobileUserId
	List<Mobile> findByMobileUserId(Long userId);
}