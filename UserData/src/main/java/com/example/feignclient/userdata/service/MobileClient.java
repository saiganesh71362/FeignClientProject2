package com.example.feignclient.userdata.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.feignclient.userdata.entity.Mobile;

@FeignClient(url = "http://localhost:7912", value = "Mobile-Client")
public interface MobileClient {

	@GetMapping("/mobile/getMobilesByUserId/{userId}")
	public List<Mobile> getMobileByUserId(@PathVariable("userId") Long userId);
}

//http://localhost:7912/mobile/getMobilesByUserId/