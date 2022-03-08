package it.be.progettosettimana12.model;

import lombok.Data;

@Data
public class LoginRequest {

	private String userName;
	private String password;

}