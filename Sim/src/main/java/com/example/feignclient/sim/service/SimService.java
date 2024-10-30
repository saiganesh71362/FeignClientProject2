package com.example.feignclient.sim.service;

import java.util.List;

import com.example.feignclient.sim.entity.Sim;
import com.example.feignclient.sim.globalexceptionhandle.IdNotFoundException;

public interface SimService {

	List<Sim> getAllSims();

	Sim getSimById(Long simId) throws IdNotFoundException;

	Sim saveSim(Sim sim);

	Sim updateSimById(Long simId, Sim sim) throws IdNotFoundException;

	Sim updateFieldById(Long simId, Sim sim) throws IdNotFoundException;

	Boolean deleteSimById(Long simId) throws IdNotFoundException;

	List<Sim> getMobileById(Long mobileId);
}
