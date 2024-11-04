package com.example.feignclient.userdata.globalexceptionhandle;

public class IdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	IdNotFoundException(String message) {
		super(message);
	}

}
