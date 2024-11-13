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
		logger.info("Fetch Request Send Get All Sims");
		List<Sim> all = simRepository.findAll();
		logger.info("Fetch Suceess Fully All Sims :{}", all.size());
		return all;
	}

	@Override
	public Sim getSimById(Long simId) throws IdNotFoundException {
		logger.info("Fetch Request Send To Get Sim By Id :{}", simId);
		Sim orElseThrow = simRepository.findById(simId).orElseThrow(() -> {
			logger.info("Fetch Request Send Thire Is No Sim :{}", simId);
			return new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
		});
		logger.info("Fetch Success Fully Get Sim By Id : {}", orElseThrow);
		return orElseThrow;
	}

	@Override
	public Sim saveSim(Sim sim) {
		logger.info("Fetch Request Send To Create New Sim :{}", sim);
		Sim save = simRepository.save(sim);
		logger.info("Sim Created Sucess Fully :{}", save.getSimId());
		return save;
	}

	@Override
	public Sim updateSimById(Long simId, Sim sim) throws IdNotFoundException {

		logger.info("Request Send to Update Sim By Id :{}", simId);
		if (simRepository.existsById(simId)) {
			Sim existingSim = simRepository.findById(simId).orElseThrow(() -> {
				logger.warn("Sim ID not found for update request :{}", simId);
				return new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
			});

			existingSim.setSimName(sim.getSimName());
			existingSim.setMobileId(sim.getMobileId());
			logger.info("Update Sim Success Fully :{}", existingSim);
			return simRepository.save(existingSim);
		} else {
			logger.warn("Sim ID not found for update request :{}", simId);
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
		logger.info("Request Send to Delete Sim By Id :{}", simId);
		if (simRepository.existsById(simId)) {
			simRepository.deleteById(simId);
			logger.info("Sim Deleted Success Fully :{}", simId);
			return true;
		} else {
			logger.warn("Sim ID not found for update request :{}", simId);
			throw new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
		}
	}

	@Override
	public List<Sim> getMobileById(Long mobileId) {
		logger.info("Fetch Request Send to Get Sims By Mobile Id :{}", mobileId);
		List<Sim> mobileById = simRepository.findByMobileId(mobileId);
		logger.info("Succss Fully Get Sim By Mobile Id :{}", mobileById);
		return mobileById;
	}

}
