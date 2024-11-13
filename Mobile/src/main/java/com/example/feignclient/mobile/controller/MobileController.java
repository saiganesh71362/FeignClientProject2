package com.example.feignclient.mobile.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private Logger logger = LogManager.getLogger(MobileController.class);

	@Autowired
	MobileServiceImpl mobileServiceImpl;

	@GetMapping("/getAllMobile")
	public ResponseEntity<List<Mobile>> getAllMobiles() {
		logger.info("Received request to fetch all mobiles data.");
		List<Mobile> allMobiles = mobileServiceImpl.getAllMobiles();
		logger.info("Successfully fetched all mobiles data, total mobiles: [{}]", allMobiles.size());
		return new ResponseEntity<>(allMobiles, HttpStatus.OK);
	}

	@GetMapping("getMobileById/{mobileId}")
	public ResponseEntity<Mobile> getMobileById(@PathVariable Long mobileId) throws Exception {
		logger.info("Received request to fetch mobile data with ID: [{}]", mobileId);
		Mobile mobileById = mobileServiceImpl.getMobileById(mobileId);
		logger.info("Successfully fetched mobile data for ID: [{}]", mobileById);
		return new ResponseEntity<>(mobileById, HttpStatus.OK);
	}

	@PostMapping("/saveNewMobile")
	public ResponseEntity<Mobile> saveMobile(@RequestBody Mobile mobile) {
		logger.info("Received request to save new mobile data: [{}]", mobile);
		Mobile saveMobile = mobileServiceImpl.saveMobile(mobile);
		logger.info("Successfully saved mobile with ID: [{}]", saveMobile);
		return new ResponseEntity<Mobile>(saveMobile, HttpStatus.CREATED);
	}

	@PutMapping("/updateMobileById/{mobileId}")
	public ResponseEntity<Mobile> updateMobileById(@PathVariable Long mobileId, @RequestBody Mobile mobile)
			throws Exception {
		logger.info("Received request to update mobile data for ID: [{}] with new Data: [{}]", mobileId, mobile);
		Mobile updateMobileById = mobileServiceImpl.updateMobileById(mobileId, mobile);
		logger.info("Successfully updated mobile with ID: [{}] ", updateMobileById);
		return new ResponseEntity<Mobile>(updateMobileById, HttpStatus.ACCEPTED);
	}

	@PatchMapping("/updateMobileFieldsById/{mobileId}")
	public ResponseEntity<Mobile> updateFieldsMobileById(@PathVariable Long mobileId, @RequestBody Mobile mobile)
			throws Exception {
		logger.info("Received request to update mobile fields data for ID: [{}] with new Data: [{}]", mobileId, mobile);

		Mobile updateFieldsMobileById = mobileServiceImpl.updateFieldsMobileById(mobileId, mobile);
		logger.info("Successfully updated mobile fields with ID: [{}] ", updateFieldsMobileById);

		return new ResponseEntity<Mobile>(updateFieldsMobileById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteMobileById/{mobileId}")
	public ResponseEntity<Boolean> deleteMobileById(@PathVariable Long mobileId) throws Exception {
		logger.info("Received request to delete mobile with ID: [{}]", mobileId);
		Boolean deleteMobileById = mobileServiceImpl.deleteMobileById(mobileId);
		logger.info("Successfully deleted mobile with ID: [{}]", deleteMobileById);
		return new ResponseEntity<Boolean>(deleteMobileById, HttpStatus.OK);
	}

	@GetMapping("/getMobilesByUserId/{userId}")
	public ResponseEntity<List<Mobile>> getMobileByUserId(@PathVariable Long userId) throws Exception {
		logger.info("Recived request to fetch mobile by UserId :[{}]", userId);
		List<Mobile> mobileByUserId = mobileServiceImpl.getMobileByUserId(userId);
		logger.info("Successfully to fetch mobile by UserId :[{}]", mobileByUserId);

		return new ResponseEntity<List<Mobile>>(mobileByUserId, HttpStatus.OK);
	}

}
