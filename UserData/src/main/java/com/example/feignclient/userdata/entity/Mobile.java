package com.example.feignclient.userdata.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mobile {

	private Long mobileId;
	private String mobileName;
	private Long userId;
	private List<Sim> sim;

}
