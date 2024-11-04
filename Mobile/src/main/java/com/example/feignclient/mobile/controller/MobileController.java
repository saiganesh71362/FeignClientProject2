package com.example.feignclient.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.feignclient.mobile.entity.Mobile;
import com.example.feignclient.mobile.serviceimpl.MobileServiceImpl;

@RestController
@RequestMapping("/mobile")
public class MobileController {

	@Autowired
	MobileServiceImpl mobileServiceImpl;

	@GetMapping("/getAllMobile")
	public ResponseEntity<List<Mobile>> getAllMobiles() {
		List<Mobile> allMobiles = mobileServiceImpl.getAllMobiles();
		return new ResponseEntity<>(allMobiles, HttpStatus.OK);
	}

	@GetMapping("getMobileById/{mobileId}")
	public ResponseEntity<Mobile> getMobileById(@PathVariable Long mobileId) throws Exception {
		Mobile mobileById = mobileServiceImpl.getMobileById(mobileId);
		return new ResponseEntity<>(mobileById, HttpStatus.OK);
	}

	@PostMapping("/saveNewMobile")
	public ResponseEntity<Mobile> saveMobile(@RequestBody Mobile mobile) {
		Mobile saveMobile = mobileServiceImpl.saveMobile(mobile);
		return new ResponseEntity<Mobile>(saveMobile, HttpStatus.CREATED);
	}

	@PutMapping("/updateMobileById/{mobileId}")
	public ResponseEntity<Mobile> updateMobileById(@PathVariable Long mobileId, @RequestBody Mobile mobile)
			throws Exception {

		Mobile updateMobileById = mobileServiceImpl.updateMobileById(mobileId, mobile);

		return new ResponseEntity<Mobile>(updateMobileById, HttpStatus.ACCEPTED);
	}

	@PatchMapping("/updateMobileFieldsById/{mobileId}")
	public ResponseEntity<Mobile> updateFieldsMobileById(@PathVariable Long mobileId, @RequestBody Mobile mobile)
			throws Exception {

		Mobile updateFieldsMobileById = mobileServiceImpl.updateFieldsMobileById(mobileId, mobile);

		return new ResponseEntity<Mobile>(updateFieldsMobileById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteMobileById/{mobileId}")
	public ResponseEntity<Boolean> deleteMobileById(@PathVariable Long mobileId) throws Exception {
		Boolean deleteMobileById = mobileServiceImpl.deleteMobileById(mobileId);
		return new ResponseEntity<Boolean>(deleteMobileById, HttpStatus.OK);
	}

	@GetMapping("/getMobilesByUserId/{userId}")
	public ResponseEntity<List<Mobile>> getMobileByUserId(@PathVariable Long userId) throws Exception {
		List<Mobile> mobileByUserId = mobileServiceImpl.getMobileByUserId(userId);
		return new ResponseEntity<List<Mobile>>(mobileByUserId, HttpStatus.OK);
	}

}
