package com.example.feignclient.userdata.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

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
		List<UserData> allUsers = userDataRepository.findAll();
		List<UserData> collect = allUsers.stream().map(user -> {
			user.setMobile(mobileClient.getMobileByUserId(user.getUserId()));
			return user;
		}).collect(Collectors.toList());
		return collect;
	}

	@Override
	public UserData getUserById(Long userId) throws Exception {

		UserData getUserById = userDataRepository.findById(userId).orElseThrow(() -> {
			return new Exception(UserDataAppConstants.ID_NOT_FOUND + userId);
		});

//		getUserById.setMobile(mobileClient.getMobileByUserId(userId));
		List<Mobile> mobileByUserId = mobileClient.getMobileByUserId(userId);
		for (Mobile mobile : mobileByUserId) {
			List<Sim> mobileById = simClient.getMobileById(userId);
			mobile.setSim(mobileById);
		}
		getUserById.setMobile(mobileByUserId);
		return getUserById;
	}

	@Override
	public UserData saveUser(UserData userData) {
		UserData save = userDataRepository.save(userData);
		return save;
	}

	@Override
	public UserData updateUser(Long userId, UserData userData) throws Exception {
		if (userDataRepository.existsById(userId)) {
			UserData existingUser = userDataRepository.findById(userId).orElseThrow(() -> {
				return new Exception(UserDataAppConstants.ID_NOT_FOUND + userId);
			});

			existingUser.setUserName(userData.getUserName());
			return userDataRepository.save(existingUser);
		} else {
			throw new Exception(UserDataAppConstants.ID_NOT_FOUND + userId);
		}
	}

	@Override
	public Boolean deleteUser(Long userId) throws Exception {
		if (userDataRepository.existsById(userId)) {
			userDataRepository.deleteById(userId);
			return true;
		} else {
			throw new Exception(UserDataAppConstants.ID_NOT_FOUND + userId);
		}
	}

}
