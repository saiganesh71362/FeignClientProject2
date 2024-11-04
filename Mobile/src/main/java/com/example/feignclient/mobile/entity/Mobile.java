package com.example.feignclient.mobile.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mobile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long mobileId;
	private String mobileName;
	private Long mobileUserId;
	transient List<Sim> sim;

}
