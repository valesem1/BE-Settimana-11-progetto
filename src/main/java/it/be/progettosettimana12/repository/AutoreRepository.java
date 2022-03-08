package it.be.progettosettimana12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.be.progettosettimana12.model.Autore;

public interface AutoreRepository extends JpaRepository<Autore, Long> {

}