package com.example.feignclient.userdata.controller;

import java.util.List;

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

	@Autowired
	UserDataServiceImpl userDataServiceImpl;

	@GetMapping("/getAllUserData")
	public ResponseEntity<List<UserData>> getAllUsers() {

		List<UserData> allUsers = userDataServiceImpl.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/getUserDataById/{userId}")
	public ResponseEntity<UserData> getUserById(@PathVariable Long userId) throws Exception {
		UserData userById = userDataServiceImpl.getUserById(userId);
		return new ResponseEntity<>(userById, HttpStatus.OK);
	}

	@PostMapping("/newUserDatSave")
	public ResponseEntity<UserData> saveUser(@RequestBody UserData userData) {
		UserData saveUser = userDataServiceImpl.saveUser(userData);
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
	}

	@PutMapping("/updateUsetData/{userId}")
	public ResponseEntity<UserData> updateUser(@PathVariable Long userId, @RequestBody UserData userData)
			throws Exception {
		UserData updateUser = userDataServiceImpl.updateUser(userId, userData);
		return new ResponseEntity<UserData>(updateUser, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteDataUser/{userId}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) throws Exception {
		Boolean deleteUser = userDataServiceImpl.deleteUser(userId);
		return new ResponseEntity<>(deleteUser, HttpStatus.OK);
	}
}
