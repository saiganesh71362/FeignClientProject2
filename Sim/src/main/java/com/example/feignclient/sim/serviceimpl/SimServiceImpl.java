package com.example.feignclient.sim.serviceimpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.feignclient.sim.appconstants.SimClassAppConstants;
import com.example.feignclient.sim.entity.Sim;
import com.example.feignclient.sim.globalexceptionhandle.IdNotFoundException;
import com.example.feignclient.sim.repository.SimRepository;
import com.example.feignclient.sim.service.SimService;

@Service
public class SimServiceImpl implements SimService {

	@Autowired
	SimRepository simRepository;
	private Logger logger = LogManager.getLogger(SimServiceImpl.class);

	@Override
	public List<Sim> getAllSims() {
		logger.info("Received request to fetch all sims.");
		List<Sim> all = simRepository.findAll();
		logger.info("Successfully fetched all sims: total count [{}]", all.size());
		return all;
	}

	@Override
	public Sim getSimById(Long simId) throws IdNotFoundException {
		logger.info("Received request to fetch get sim by id :{}", simId);
		Sim orElseThrow = simRepository.findById(simId).orElseThrow(() -> {
			logger.info("Received request sent. No sim found with ID: {}", simId);
			return new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
		});
		logger.info("Success fully fetch get sims record :{}", orElseThrow);
		return orElseThrow;
	}

	@Override
	public Sim saveSim(Sim sim) {
		logger.info("Received request to save new sim: [{}]", sim);
		Sim save = simRepository.save(sim);
		logger.info("Successfully saved sim with ID: [{}]", save.getSimId());
		return save;
	}

	@Override
	public Sim updateSimById(Long simId, Sim sim) throws IdNotFoundException {

		logger.info("Received request to update sim with ID: [{}], new data: [{}]", simId, sim);
		if (simRepository.existsById(simId)) {
			Sim existingSim = simRepository.findById(simId).orElseThrow(() -> {
				logger.warn("Sim with ID: [{}] not found during update operation", simId);
				return new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
			});

			existingSim.setSimName(sim.getSimName());
			existingSim.setMobileId(sim.getMobileId());
			logger.info("Successfully updated sim with ID: [{}]", existingSim);
			return simRepository.save(existingSim);
		} else {
			logger.warn("Sim with ID: [{}] not found, cannot proceed with update", simId);
			throw new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
		}

	}

	@Override
	public Sim updateFieldById(Long simId, Sim sim) throws IdNotFoundException {
		logger.info("Request Send to Update Sim By Id Specific Field :{}", simId);

		if (simRepository.existsById(simId)) {
			Sim existingSim = simRepository.findById(simId).orElseThrow(() -> {
				logger.warn("Sim ID not found for update request :{}", simId);

				return new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
			});

			existingSim.setSimName(sim.getSimName());
			logger.info("Update Sim Fields Success Fully :{}", existingSim);

			return simRepository.save(sim);
		} else {
			logger.warn("Sim ID not found for update request :{}", simId);
			throw new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
		}
	}

	@Override
	public Boolean deleteSimById(Long simId) throws IdNotFoundException {
		logger.info("Received request to delete sim with ID: [{}]", simId);
		if (simRepository.existsById(simId)) {
			simRepository.deleteById(simId);
			logger.info("Successfully deleted sim with ID: [{}]", simId);
			return true;
		} else {
			logger.warn("Sim with ID: [{}] not found, cannot proceed with deletion", simId);
			throw new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
		}
	}

	@Override
	public List<Sim> getMobileById(Long mobileId) {
		logger.info("Received request to fetch sim with mobile  ID: {}", mobileId);
		List<Sim> mobileById = simRepository.findByMobileId(mobileId);
		logger.info("Successfully fetched sim  ID: {}", mobileById);
		return mobileById;
	}

}
