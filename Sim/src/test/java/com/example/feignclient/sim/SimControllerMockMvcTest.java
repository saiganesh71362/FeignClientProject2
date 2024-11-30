package com.example.feignclient.sim;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.feignclient.sim.controller.SimController;
import com.example.feignclient.sim.entity.Sim;
import com.example.feignclient.sim.serviceimpl.SimServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ContextConfiguration
@ComponentScan("com.example.feignclient.sim")
@AutoConfigureMockMvc
class SimControllerMockMvcTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	SimServiceImpl simServiceImpl;

	@InjectMocks
	SimController simController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(simController).build();
	}

	@Test
	@Order(1)
	void test_getAllSims() throws Exception {

		ArrayList<Sim> simsList = new ArrayList<>();

		Sim sim1 = new Sim();
		sim1.setSimId(1l);
		sim1.setSimName("Airtel");
		sim1.setMobileId(1l);

		Sim sim2 = new Sim();
		sim2.setSimId(2l);
		sim2.setSimName("Jio");
		sim2.setMobileId(1l);

		simsList.add(sim1);
		simsList.add(sim2);

		when(simServiceImpl.getAllSims()).thenReturn(simsList);

		this.mockMvc.perform(get("/sim/getAllSim")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	@Order(2)
	void test_getSimById() throws Exception {
		Sim sim1 = new Sim();
		sim1.setSimId(1l);
		sim1.setSimName("Airtel");
		sim1.setMobileId(1l);

		when(simServiceImpl.getSimById(1l)).thenReturn(sim1);

		this.mockMvc.perform(get("/sim/getSimById/{simId}", 1l)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(3)
	void test_saveSim() throws Exception {
		Sim sim1 = new Sim();
		sim1.setSimId(1L);
		sim1.setSimName("Airtel");
		sim1.setMobileId(1L);

		when(simServiceImpl.saveSim(sim1)).thenReturn(sim1);

		this.mockMvc
				.perform(post("/sim/newSimAdd")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(sim1)))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	
//	.andExpect(jsonPath("$.simId").value(1L))
//    .andExpect(jsonPath("$.simName").value("Airtel"))
//    .andExpect(jsonPath("$.mobileId").value(1L))
	
	
}
