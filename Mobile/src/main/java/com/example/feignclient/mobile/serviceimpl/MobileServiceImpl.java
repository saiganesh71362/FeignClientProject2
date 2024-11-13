package com.example.feignclient.mobile.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.feignclient.mobile.appconstants.MobileAppConstants;
import com.example.feignclient.mobile.entity.Mobile;
import com.example.feignclient.mobile.globalexceptionhandle.IdNotFoundException;
import com.example.feignclient.mobile.repository.MobileRepository;
import com.example.feignclient.mobile.service.MobileService;
import com.example.feignclient.mobile.service.SimClient;

@Service
public class MobileServiceImpl implements MobileService {

	private Logger logger = LogManager.getLogger(MobileServiceImpl.class);

	MobileRepository mobileRepository;
	SimClient simClient;

	public MobileServiceImpl(MobileRepository mobileRepository, SimClient simClient) {
		super();
		this.mobileRepository = mobileRepository;
		this.simClient = simClient;
	}

	@Override
	public List<Mobile> getAllMobiles() {
		logger.info("Fetch Request Send Get All Mobiles With Sims");
		List<Mobile> getAllMobiles = mobileRepository.findAll();

		List<Mobile> collectMobiles = getAllMobiles.stream().map(sim -> {
			sim.setSim(simClient.getMobileById(sim.getMobileId()));
			return sim;
		}).collect(Collectors.toList());

		logger.info("Success Fully Fetch All Mobile :{}", getAllMobiles);
		return collectMobiles;
	}

	@Override
	public Mobile getMobileById(Long mobileId) throws IdNotFoundException {
		Mobile getMobileById = mobileRepository.findById(mobileId).orElseThrow(() -> {
			return new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobileId);
		});

		getMobileById.setSim(simClient.getMobileById(mobileId));
		return getMobileById;
	}

	@Override
	public Mobile saveMobile(Mobile mobile) {
		Mobile save = mobileRepository.save(mobile);
		return save;
	}

	@Override
	public Mobile updateMobileById(Long mobileId, Mobile mobile) throws IdNotFoundException {
		if (mobileRepository.existsById(mobileId)) {
			Mobile existingMobile = mobileRepository.findById(mobileId).orElseThrow(() -> {
				return new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobile);
			});

			existingMobile.setMobileName(mobile.getMobileName());
			existingMobile.setMobileUserId(mobile.getMobileUserId());
			return mobileRepository.save(existingMobile);
		} else {
			throw new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobileId);
		}
	}

	@Override
	public Mobile updateFieldsMobileById(Long mobileId, Mobile mobile) throws IdNotFoundException {

		if (mobileRepository.existsById(mobileId)) {
			Mobile existingMobile = mobileRepository.findById(mobileId).orElseThrow(() -> {
				return new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobile);
			});

			existingMobile.setMobileName(mobile.getMobileName());
			return mobileRepository.save(existingMobile);
		} else {
			throw new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobileId);
		}
	}

	@Override
	public Boolean deleteMobileById(Long mobileId) throws IdNotFoundException {
		if (mobileRepository.existsById(mobileId)) {
			mobileRepository.deleteById(mobileId);
			return true;
		} else {
			throw new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobileId);
		}
	}

	@Override
	public List<Mobile> getMobileByUserId(Long userId) throws IdNotFoundException {

		if (mobileRepository.existsById(userId)) {
			List<Mobile> byMobileUserId = mobileRepository.findByMobileUserId(userId);
			List<Mobile> collect = byMobileUserId.stream().map(sim -> {
				sim.setSim(simClient.getMobileById(sim.getMobileId()));
				return sim;
			}).collect(Collectors.toList());
			return collect;
		}

		else {
			throw new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + userId);
		}

	}

}
