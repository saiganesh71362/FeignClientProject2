package com.example.feignclient.sim.controller;

import java.util.List;

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

	@Autowired
	SimServiceImpl simServiceImpl;

	@GetMapping("/getAllSim")
	public ResponseEntity<List<Sim>> getAllSims() {
		List<Sim> allSims = simServiceImpl.getAllSims();
		return new ResponseEntity<>(allSims, HttpStatus.OK);
	}

	@GetMapping("/getSimById/{simId}")
	public ResponseEntity<Sim> getSimById(@PathVariable Long simId) throws IdNotFoundException {
		Sim simById = simServiceImpl.getSimById(simId);
		return new ResponseEntity<>(simById, HttpStatus.OK);
	}

	@PostMapping("/newSimAdd")
	public ResponseEntity<Sim> saveSim(@RequestBody Sim sim) {
		Sim saveSim = simServiceImpl.saveSim(sim);
		return new ResponseEntity<>(saveSim, HttpStatus.CREATED);
	}

	@PutMapping("updateSimById/{simId}")
	public ResponseEntity<Sim> updateSimById(@PathVariable Long simId, @RequestBody Sim sim)
			throws IdNotFoundException {
		Sim updateSimById = simServiceImpl.updateSimById(simId, sim);
		return new ResponseEntity<>(updateSimById, HttpStatus.ACCEPTED);
	}

	@PatchMapping("updateSimById/{simId}")
	public ResponseEntity<Sim> updateFieldById(@PathVariable Long simId, @RequestBody Sim sim)
			throws IdNotFoundException {
		Sim updateFieldById = simServiceImpl.updateFieldById(simId, sim);
		return new ResponseEntity<>(updateFieldById, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteSimById/{simId}")
	public ResponseEntity<Boolean> deleteSimById(@PathVariable Long simId) throws IdNotFoundException {
		Boolean deleteSimById = simServiceImpl.deleteSimById(simId);
		return new ResponseEntity<>(deleteSimById, HttpStatus.OK);
	}

	@GetMapping("/getSimByMobileId/{mobileId}")
	public ResponseEntity<List<Sim>> getMobileById(@PathVariable Long mobileId) {
		List<Sim> mobileById = simServiceImpl.getMobileById(mobileId);
		return new ResponseEntity<List<Sim>>(mobileById, HttpStatus.OK);
	}

}
