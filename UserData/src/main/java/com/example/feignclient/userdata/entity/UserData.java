package com.example.feignclient.userdata.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String userName;
	
	transient List<Mobile> mobile;
}
