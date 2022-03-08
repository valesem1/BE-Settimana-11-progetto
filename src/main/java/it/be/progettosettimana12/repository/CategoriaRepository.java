package it.be.progettosettimana12.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.be.progettosettimana12.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}