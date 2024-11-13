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
		logger.info("Received Request Get All Sims");
		List<Sim> allSims = simServiceImpl.getAllSims();
		logger.info("Success Fully Get All Sims :{}", allSims);
		return new ResponseEntity<>(allSims, HttpStatus.OK);
	}

	@GetMapping("/getSimById/{simId}")
	public ResponseEntity<Sim> getSimById(@PathVariable Long simId) throws IdNotFoundException {
		logger.info("Received Request Get Sim By Id :{}", simId);
		Sim simById = simServiceImpl.getSimById(simId);
		logger.info("Success Fully Get  Sim By Id :{}", simById);

		return new ResponseEntity<>(simById, HttpStatus.OK);
	}

	@PostMapping("/newSimAdd")
	public ResponseEntity<Sim> saveSim(@RequestBody Sim sim) {
		logger.info(" Received Request  To Create New Sim:{}", sim);
		Sim saveSim = simServiceImpl.saveSim(sim);
		logger.info("Success Fully Created New Sim :{}", saveSim);
		return new ResponseEntity<>(saveSim, HttpStatus.CREATED);
	}

	@PutMapping("updateSimById/{simId}")
	public ResponseEntity<Sim> updateSimById(@PathVariable Long simId, @RequestBody Sim sim)
			throws IdNotFoundException {
		logger.info("Request Received To Update Sim By Id :{}", simId);
		Sim updateSimById = simServiceImpl.updateSimById(simId, sim);
		logger.info("Success Fully Update Sim :{}", sim);
		return new ResponseEntity<>(updateSimById, HttpStatus.ACCEPTED);
	}

	@PatchMapping("updateSimById/{simId}")
	public ResponseEntity<Sim> updateFieldById(@PathVariable Long simId, @RequestBody Sim sim)
			throws IdNotFoundException {
		logger.info("Received request to update specific fields of Sim with ID :{}", simId);

		Sim updateFieldById = simServiceImpl.updateFieldById(simId, sim);
		logger.info("Successfully updated specific fields of Sim: {}", sim);

		return new ResponseEntity<>(updateFieldById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteSimById/{simId}")
	public ResponseEntity<Boolean> deleteSimById(@PathVariable Long simId) throws IdNotFoundException {
		logger.info(" Received Request Delete Sim By Id:{} ", simId);
		Boolean deleteSimById = simServiceImpl.deleteSimById(simId);
		logger.info("Sucess Fully Delete Sim By Id :{} ", simId);
		return new ResponseEntity<>(deleteSimById, HttpStatus.OK);
	}

	@GetMapping("/getSimByMobileId/{mobileId}")
	public ResponseEntity<List<Sim>> getMobileById(@PathVariable Long mobileId) {
		logger.info("Received Request Get Sim With Mobile Id :{}", mobileId);
		List<Sim> mobileById = simServiceImpl.getMobileById(mobileId);
		logger.info("Success Fully Get Sims With Mobile Id :{}", mobileById);
		return new ResponseEntity<List<Sim>>(mobileById, HttpStatus.OK);
	}

}
