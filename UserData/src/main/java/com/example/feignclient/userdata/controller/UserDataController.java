package com.example.feignclient.userdata.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.feignclient.userdata.entity.UserData;
import com.example.feignclient.userdata.serviceimpl.UserDataServiceImpl;

@RestController
@RequestMapping("/user")
public class UserDataController {

	private Logger logger = LogManager.getLogger(UserDataController.class);

	@Autowired
	UserDataServiceImpl userDataServiceImpl;

	@GetMapping("/getAllUserData")
	public ResponseEntity<List<UserData>> getAllUsers() {
		logger.info("Received request to fetch all user data.");
		List<UserData> allUsers = userDataServiceImpl.getAllUsers();
		logger.info("Successfully fetched all user data, total users: [{}]", allUsers.size());
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/getUserDataById/{userId}")
	public ResponseEntity<UserData> getUserById(@PathVariable Long userId) throws Exception {
		logger.info("Received request to fetch user data with ID: [{}]", userId);
		UserData userById = userDataServiceImpl.getUserById(userId);
		logger.info("Successfully fetched user data for ID: [{}]", userId);
		return new ResponseEntity<>(userById, HttpStatus.OK);
	}

	@PostMapping("/newUserDatSave")
	public ResponseEntity<UserData> saveUser(@RequestBody UserData userData) {
		logger.info("Received request to save new user data: [{}]", userData);

		UserData savedUser = userDataServiceImpl.saveUser(userData);
		logger.info("Successfully saved user with ID: [{}]", savedUser.getUserId());

		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@PutMapping("/updateUsetData/{userId}")
	public ResponseEntity<UserData> updateUser(@PathVariable Long userId, @RequestBody UserData userData)
			throws Exception {
		logger.info("Received request to update user data for ID: [{}] with new data: [{}]", userId, userData);

		UserData updateUser = userDataServiceImpl.updateUser(userId, userData);
		logger.info("Successfully updated user with ID: [{}]", userId);

		return new ResponseEntity<UserData>(updateUser, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteDataUser/{userId}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) throws Exception {
		logger.info("Received request to delete user with ID: [{}]", userId);

		Boolean deleteUser = userDataServiceImpl.deleteUser(userId);
		logger.info("Successfully deleted user with ID: [{}]", userId);

		return new ResponseEntity<>(deleteUser, HttpStatus.OK);
	}
}
