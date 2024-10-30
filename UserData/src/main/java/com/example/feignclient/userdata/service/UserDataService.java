package com.example.feignclient.userdata.service;

import java.util.List;

import com.example.feignclient.userdata.entity.UserData;

public interface UserDataService {

	public List<UserData> getAllUsers();

	public UserData getUserById(Long userId);

	public UserData saveUser(UserData userData);

	public UserData updateUser(Long userId, UserData userData);

	public Boolean deleteUser(Long userId);

}
