package com.example.feignclient.mobile.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.feignclient.mobile.entity.Sim;

@FeignClient(url = "http://localhost:7913", value = "Sim-Client")
public interface SimClient {

	@GetMapping("/sim/getSimByMobileId/{mobileId}")
	public List<Sim> getMobileById(@PathVariable("mobileId") Long mobileId);

}
