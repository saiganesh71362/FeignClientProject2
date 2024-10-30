package com.example.feignclient.userdata.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.feignclient.userdata.entity.UserData;
import com.example.feignclient.userdata.repository.UserDataRepository;
import com.example.feignclient.userdata.service.UserDataService;

@Service
public class UserDataServiceImpl implements UserDataService {

	@Autowired
	UserDataRepository userDataRepository;
	
	@Override
	public List<UserData> getAllUsers() {
		List<UserData> all = userDataRepository.findAll();
		return null;
	}

	@Override
	public UserData getUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserData saveUser(UserData userData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserData updateUser(Long userId, UserData userData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
