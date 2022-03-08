package it.be.progettosettimana12.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.be.progettosettimana12.exception.CatalogoException;
import it.be.progettosettimana12.model.Autore;
import it.be.progettosettimana12.repository.AutoreRepository;

@Service
public class AutoreService {

	@Autowired
	AutoreRepository autoreRepository;

	public void saveAllFromSet(Set<Autore> autori) {
		for (Autore autore : autori) {
			autoreRepository.save(autore);
		}
	}

	public List<Autore> findAllAutori() {
		return autoreRepository.findAll();
	}

	public Optional<Autore> findAutoreById(Long id) {
		return autoreRepository.findById(id);
	}

	public Autore addAutore(Autore autore) {
		return autoreRepository.save(autore);
	}

	public Autore updateAutore(Long id, Autore autore) {
		Optional<Autore> trovato = autoreRepository.findById(id);
		if (trovato.isPresent()) {
			Autore update = trovato.get();
			update.setCognome(autore.getCognome());
			update.setNome(autore.getNome());
			update.setId(autore.getId());
			autoreRepository.save(update);
			return update;
		} 
		else {
			throw new CatalogoException("ERRORE!");
		}
	}

	public void deleteAutoreById(Long id) {
		autoreRepository.deleteById(id);
	}

}