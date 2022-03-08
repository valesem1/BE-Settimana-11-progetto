package it.be.progettosettimana12.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.be.progettosettimana12.model.User;
import it.be.progettosettimana12.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

}