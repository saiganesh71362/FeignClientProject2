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
		logger.info("Received request to fetch all mobiles.");
		List<Mobile> getAllMobiles = mobileRepository.findAll();

		List<Mobile> collectMobiles = getAllMobiles.stream().map(sim -> {
			sim.setSim(simClient.getMobileById(sim.getMobileId()));
			return sim;
		}).collect(Collectors.toList());

		logger.info("Successfully fetched all mobiles: total count [{}]", getAllMobiles);
		return collectMobiles;
	}

	@Override
	public Mobile getMobileById(Long mobileId) throws IdNotFoundException {
		logger.info("Received request to fetch get mobile by id :{}", mobileId);
		Mobile getMobileById = mobileRepository.findById(mobileId).orElseThrow(() -> {
			logger.info("Received request sent. No mobile found with ID: {}", mobileId);
			return new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobileId);
		});

		getMobileById.setSim(simClient.getMobileById(mobileId));
		logger.info("Success fully fetch get mobiles record :{}", getMobileById);
		return getMobileById;
	}

	@Override
	public Mobile saveMobile(Mobile mobile) {
		logger.info("Received request to save new mobile: [{}]", mobile);
		Mobile save = mobileRepository.save(mobile);
		logger.info("Successfully saved mobile with ID: [{}]", save.getMobileId());
		return save;
	}

	@Override
	public Mobile updateMobileById(Long mobileId, Mobile mobile) throws IdNotFoundException {
		logger.info("Received request to update mobile with ID: [{}], new data: [{}]", mobileId,mobile);
		if (mobileRepository.existsById(mobileId)) {
			Mobile existingMobile = mobileRepository.findById(mobileId).orElseThrow(() -> {
				logger.info("Mobile with ID: [{}] not found during update operation", mobileId);
				return new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobile);
			});

			existingMobile.setMobileName(mobile.getMobileName());
			existingMobile.setMobileUserId(mobile.getMobileUserId());
			logger.info("Successfully updated mobile with ID: [{}]", mobileId);

			return mobileRepository.save(existingMobile);
		} else {
			logger.info("Mobile with ID: [{}] not found, cannot proceed with update", mobileId);
			throw new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobileId);
		}
	}

	@Override
	public Mobile updateFieldsMobileById(Long mobileId, Mobile mobile) throws IdNotFoundException {
		logger.info("Received request to update mobile fields with ID: [{}], new data: [{}]", mobileId,mobile);
		if (mobileRepository.existsById(mobileId)) {
			Mobile existingMobile = mobileRepository.findById(mobileId).orElseThrow(() -> {
				logger.info("Mobile with ID: [{}] not found during update operation", mobileId);
				return new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobile);
			});

			existingMobile.setMobileName(mobile.getMobileName());
			logger.info("Successfully updated mobile with ID: [{}]", mobileId , existingMobile);

			return mobileRepository.save(existingMobile);
		} else {
			logger.info("Mobile with ID: [{}] not found, cannot proceed with update", mobileId);
			throw new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobileId);
		}
	}

	@Override
	public Boolean deleteMobileById(Long mobileId) throws IdNotFoundException {
		logger.info("Received request to delete mobile with ID: [{}]", mobileId);
		if (mobileRepository.existsById(mobileId)) {
			mobileRepository.deleteById(mobileId);
			logger.info("Successfully deleted mobile with ID: [{}]", mobileId);
			return true;
		} else {
			logger.info("Mobile with ID: [{}] not found, cannot proceed with deletion", mobileId);

			throw new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + mobileId);
		}
	}

	@Override
	public List<Mobile> getMobileByUserId(Long userId) throws IdNotFoundException {
		logger.info("Received request to fetch mobiles for user ID: {}", userId);

		if (mobileRepository.existsById(userId)) {
			List<Mobile> byMobileUserId = mobileRepository.findByMobileUserId(userId);
			List<Mobile> collect = byMobileUserId.stream().map(sim -> {
				sim.setSim(simClient.getMobileById(sim.getMobileId()));
				return sim;
			}).collect(Collectors.toList());
			logger.info("Successfully fetched mobiles for user ID: {}", byMobileUserId);

			return collect;
		}

		else {
			logger.info("No mobiles found for user ID: {}", userId);

			throw new IdNotFoundException(MobileAppConstants.ID_NOT_FOUND + userId);
		}

	}

}
