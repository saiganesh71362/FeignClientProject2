package com.example.feignclient.mobile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sim {
	
	private Long simId;
	private String simName;
	private Long mobileId;

}
