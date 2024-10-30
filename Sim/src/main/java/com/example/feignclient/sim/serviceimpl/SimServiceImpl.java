package com.example.feignclient.sim.serviceimpl;

import java.util.List;

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

	@Override
	public List<Sim> getAllSims() {
		List<Sim> all = simRepository.findAll();

		return all;
	}

	@Override
	public Sim getSimById(Long simId) throws IdNotFoundException {

		Sim orElseThrow = simRepository.findById(simId).orElseThrow(() -> {
			return new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
		});
		return orElseThrow;
	}

	@Override
	public Sim saveSim(Sim sim) {

		Sim save = simRepository.save(sim);
		return save;
	}

	@Override
	public Sim updateSimById(Long simId, Sim sim) throws IdNotFoundException {

		if (simRepository.existsById(simId)) {
			Sim existingSim = simRepository.findById(simId).orElseThrow(() -> {
				return new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
			});

			existingSim.setSimName(sim.getSimName());
			existingSim.setMobileId(sim.getMobileId());
			return simRepository.save(existingSim);
		} else {
			throw new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
		}

	}

	@Override
	public Sim updateFieldById(Long simId, Sim sim) throws IdNotFoundException {

		if (simRepository.existsById(simId)) {
			Sim existingSim = simRepository.findById(simId).orElseThrow(() -> {
				return new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
			});

			existingSim.setSimName(sim.getSimName());
			return simRepository.save(sim);
		} else {
			throw new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
		}
	}

	@Override
	public Boolean deleteSimById(Long simId) throws IdNotFoundException {
		if (simRepository.existsById(simId)) {
			simRepository.deleteById(simId);
			return true;
		} else {
			throw new IdNotFoundException(SimClassAppConstants.ID_NOT_FOUND + simId);
		}
	}

	@Override
	public List<Sim> getMobileById(Long mobileId) {
		List<Sim> mobileById = simRepository.findByMobileId(mobileId);
		return mobileById;
	}

}
