package com.example.feignclient.userdata.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.feignclient.userdata.appconstants.UserDataAppConstants;
import com.example.feignclient.userdata.entity.Mobile;
import com.example.feignclient.userdata.entity.Sim;
import com.example.feignclient.userdata.entity.UserData;
import com.example.feignclient.userdata.repository.UserDataRepository;
import com.example.feignclient.userdata.service.MobileClient;
import com.example.feignclient.userdata.service.SimClient;
import com.example.feignclient.userdata.service.UserDataService;

@Service
public class UserDataServiceImpl implements UserDataService {

	private Logger logger = LogManager.getLogger(UserDataServiceImpl.class);

	UserDataRepository userDataRepository;

	MobileClient mobileClient;

	SimClient simClient;

	public UserDataServiceImpl(UserDataRepository userDataRepository, MobileClient mobileClient, SimClient simClient) {
		super();
		this.userDataRepository = userDataRepository;
		this.mobileClient = mobileClient;
		this.simClient = simClient;
	}

	@Override
	public List<UserData> getAllUsers() {
		logger.info("Received request to fetch all users.");
		List<UserData> allUsers = userDataRepository.findAll();
		List<UserData> collect = allUsers.stream().map(user -> {
			user.setMobile(mobileClient.getMobileByUserId(user.getUserId()));
			return user;
		}).collect(Collectors.toList());
		logger.info("Successfully fetched all users: total count [{}]", allUsers.size());
		return collect;
	}

	@Override
	public UserData getUserById(Long userId) throws Exception {

		logger.info("Received request to fetch get users by id :{}", userId);

		UserData getUserById = userDataRepository.findById(userId).orElseThrow(() -> {
			return new Exception(UserDataAppConstants.ID_NOT_FOUND + userId);
		});
		List<Mobile> mobileByUserId = mobileClient.getMobileByUserId(userId);
		for (Mobile mobile : mobileByUserId) {
			List<Sim> mobileById = simClient.getMobileById(userId);
			mobile.setSim(mobileById);
		}
		getUserById.setMobile(mobileByUserId);
		logger.info("Success fully fetch get users record :{}", getUserById);
		return getUserById;
	}

	@Override
	public UserData saveUser(UserData userData) {
		logger.info("Received request to save new user: [{}]", userData);
		UserData savedUser = userDataRepository.save(userData);
		logger.info("Successfully saved user with ID: [{}]", savedUser.getUserId());
		return savedUser;
	}

	@Override
	public UserData updateUser(Long userId, UserData userData) throws Exception {
		logger.info("Received request to update user with ID: [{}], new data: [{}]", userId, userData);
		if (userDataRepository.existsById(userId)) {
			UserData existingUser = userDataRepository.findById(userId).orElseThrow(() -> {
				logger.error("User with ID: [{}] not found during update operation", userId);
				return new Exception(UserDataAppConstants.ID_NOT_FOUND + userId);
			});

			existingUser.setUserName(userData.getUserName());
			logger.info("Successfully updated user with ID: [{}]", userId);

			return userDataRepository.save(existingUser);
		} else {
			logger.error("User with ID: [{}] not found, cannot proceed with update", userId);

			throw new Exception(UserDataAppConstants.ID_NOT_FOUND + userId);
		}
	}

	@Override
	public Boolean deleteUser(Long userId) throws Exception {
		logger.info("Received request to delete user with ID: [{}]", userId);

		if (userDataRepository.existsById(userId)) {
			userDataRepository.deleteById(userId);
			logger.info("Successfully deleted user with ID: [{}]", userId);

			return true;
		} else {
			logger.error("User with ID: [{}] not found, cannot proceed with deletion", userId);

			throw new Exception(UserDataAppConstants.ID_NOT_FOUND + userId);
		}
	}

}
