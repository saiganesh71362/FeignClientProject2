package com.example.feignclient.sim.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.feignclient.sim.entity.Sim;
import com.example.feignclient.sim.globalexceptionhandle.IdNotFoundException;
import com.example.feignclient.sim.serviceimpl.SimServiceImpl;

@RestController
@RequestMapping("/sim")
public class SimController {

	private Logger logger = LogManager.getLogger(SimController.class);
	@Autowired
	SimServiceImpl simServiceImpl;

	@GetMapping("/getAllSim")
	public ResponseEntity<List<Sim>> getAllSims() {
		logger.info("Received request to fetch all sims data.");
		List<Sim> allSims = simServiceImpl.getAllSims();
		logger.info("Successfully fetched all sims data, total sims: [{}]", allSims.size());
		return new ResponseEntity<>(allSims, HttpStatus.OK);
	}

	@GetMapping("/getSimById/{simId}")
	public ResponseEntity<Sim> getSimById(@PathVariable Long simId) throws IdNotFoundException {
		logger.info("Received request to fetch sim data with ID: [{}]", simId);
		Sim simById = simServiceImpl.getSimById(simId);
		logger.info("Successfully fetched sim data for ID: [{}]", simById);

		return new ResponseEntity<>(simById, HttpStatus.OK);
	}

	@PostMapping("/newSimAdd")
	public ResponseEntity<Sim> saveSim(@RequestBody Sim sim) {
		logger.info("Received request to save new sim data: [{}]", sim);
		Sim saveSim = simServiceImpl.saveSim(sim);
		logger.info("Successfully saved sim with ID: [{}]", saveSim);
		return new ResponseEntity<>(saveSim, HttpStatus.CREATED);
	}

	@PutMapping("updateSimById/{simId}")
	public ResponseEntity<Sim> updateSimById(@PathVariable Long simId, @RequestBody Sim sim)
			throws IdNotFoundException {
		logger.info("Received request to update sim data for ID: [{}] with new Data: [{}]", simId, sim);
		Sim updateSimById = simServiceImpl.updateSimById(simId, sim);
		logger.info("Successfully updated sim with ID: [{}]", sim);
		return new ResponseEntity<>(updateSimById, HttpStatus.ACCEPTED);
	}

	@PatchMapping("updateSimById/{simId}")
	public ResponseEntity<Sim> updateFieldById(@PathVariable Long simId, @RequestBody Sim sim)
			throws IdNotFoundException {
		logger.info("Received request to update sim fields data for ID: [{}] with new Data: [{}]", simId, sim);

		Sim updateFieldById = simServiceImpl.updateFieldById(simId, sim);
		logger.info("Successfully updated sim fields with ID: [{}]", sim);

		return new ResponseEntity<>(updateFieldById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteSimById/{simId}")
	public ResponseEntity<Boolean> deleteSimById(@PathVariable Long simId) throws IdNotFoundException {
		logger.info("Received request to delete sim with ID: [{}]", simId);
		Boolean deleteSimById = simServiceImpl.deleteSimById(simId);
		logger.info("Successfully deleted sim with ID: [{}]", simId);
		return new ResponseEntity<>(deleteSimById, HttpStatus.OK);
	}

	@GetMapping("/getSimByMobileId/{mobileId}")
	public ResponseEntity<List<Sim>> getMobileById(@PathVariable Long mobileId) {
		logger.info("Received request to fetch sim with mobile Id :[{}]", mobileId);
		List<Sim> mobileById = simServiceImpl.getMobileById(mobileId);
		logger.info("Successfully get sims with mobile Id :[{}]", mobileById);
		return new ResponseEntity<List<Sim>>(mobileById, HttpStatus.OK);
	}

}
