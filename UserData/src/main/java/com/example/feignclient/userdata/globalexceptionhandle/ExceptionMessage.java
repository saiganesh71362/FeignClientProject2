package com.example.feignclient.userdata.globalexceptionhandle;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {

	private String message;
	private String description;
	private Date date;

}
