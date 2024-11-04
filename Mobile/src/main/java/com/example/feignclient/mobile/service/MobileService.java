package com.example.feignclient.mobile.service;

import java.util.List;

import com.example.feignclient.mobile.entity.Mobile;
import com.example.feignclient.mobile.globalexceptionhandle.IdNotFoundException;

public interface MobileService {

	List<Mobile> getAllMobiles();

	Mobile getMobileById(Long mobileId) throws IdNotFoundException;

	Mobile saveMobile(Mobile mobile);

	Mobile updateMobileById(Long mobileId, Mobile mobile) throws IdNotFoundException;

	Mobile updateFieldsMobileById(Long mobileId, Mobile mobile) throws IdNotFoundException;

	Boolean deleteMobileById(Long mobileId) throws IdNotFoundException;

	List<Mobile> getMobileByUserId(Long mobileUserId) throws IdNotFoundException;
}
