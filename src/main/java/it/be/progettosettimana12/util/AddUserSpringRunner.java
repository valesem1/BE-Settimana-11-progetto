package it.be.progettosettimana12.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.be.progettosettimana12.model.Role;
import it.be.progettosettimana12.model.Roles;
import it.be.progettosettimana12.model.User;
import it.be.progettosettimana12.repository.RoleRepository;
import it.be.progettosettimana12.repository.UserRepository;

@Component
public class AddUserSpringRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

		Role role = new Role();
		role.setRoleName(Roles.ROLE_ADMIN);
		User user = new User();
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setUserName("admin");
		user.setPassword(bCrypt.encode("admin"));
		user.setEmail("ad@new.com");
		user.setRoles(roles);
		user.setActive(true);

		roleRepository.save(role);
		userRepository.save(user);

		Role role2 = new Role();
		role2.setRoleName(Roles.ROLE_USER);
		User user2 = new User();
		Set<Role> roles2 = new HashSet<>();
		roles2.add(role2);
		user2.setUserName("user");
		user2.setPassword(bCrypt.encode("user"));
		user2.setEmail("us@new.com");
		user2.setRoles(roles2);
		user2.setActive(true);

		roleRepository.save(role2);
		userRepository.save(user2);
	}

}