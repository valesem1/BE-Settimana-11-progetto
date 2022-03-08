package it.be.progettosettimana12.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import it.be.progettosettimana12.model.Role;
import it.be.progettosettimana12.model.Roles;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleName(Roles role);

}