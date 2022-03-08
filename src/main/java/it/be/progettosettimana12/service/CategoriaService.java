package it.be.progettosettimana12.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.be.progettosettimana12.exception.CatalogoException;
import it.be.progettosettimana12.model.Categoria;
import it.be.progettosettimana12.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public void saveAllFromSet(Set<Categoria> categorie) {
		for (Categoria categoria : categorie) {
			categoriaRepository.save(categoria);
		}
	}

	public List<Categoria> findAllCategorie() {
		return categoriaRepository.findAll();
	}

	public Optional<Categoria> findCategoriaById(Long id) {
		return categoriaRepository.findById(id);
	}

	public Categoria addCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria updateCategoria(Long id, Categoria categoria) {
		Optional<Categoria> trovata = categoriaRepository.findById(id);
		if (trovata.isPresent()) {
			Categoria update = trovata.get();
			update.setNome(categoria.getNome());
			update.setId(categoria.getId());
			categoriaRepository.save(update);
			return update;
		} 
		else {
			throw new CatalogoException("ERRORE!");
		}
	}

	public void deleteCategoriaById(Long id) {
		categoriaRepository.deleteById(id);
	}

}