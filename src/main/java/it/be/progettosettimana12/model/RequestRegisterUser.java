package it.be.progettosettimana12.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class RequestRegisterUser {

	private String userName;
	private String password;
	private String email;
	private Set<String> roles = new HashSet<>();

}