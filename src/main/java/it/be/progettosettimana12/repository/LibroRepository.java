package it.be.progettosettimana12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.be.progettosettimana12.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}