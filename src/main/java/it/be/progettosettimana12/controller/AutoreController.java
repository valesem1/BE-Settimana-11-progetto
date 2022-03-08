package it.be.progettosettimana12.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.progettosettimana12.model.Autore;
import it.be.progettosettimana12.model.Libro;
import it.be.progettosettimana12.service.AutoreService;
import it.be.progettosettimana12.service.LibroService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class AutoreController {

	@Autowired
	private AutoreService autoreService;

	@Autowired
	private LibroService libroService;

	@GetMapping("/findallAutori")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti gli autori disponibili")
	public ResponseEntity<List<Autore>> findAllAutore() {
		List<Autore> findAll = autoreService.findAllAutori();
		if (!findAll.isEmpty()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/getAutore/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova un determinato autore tramite il suo id", description = "Inserire un ID di un autore presente nel database")
	public ResponseEntity<Autore> findAutoreById(@PathVariable(required = true) Long id) {
		Optional<Autore> find = autoreService.findAutoreById(id);
		if (find.isEmpty()) {
			return new ResponseEntity<>(find.get(), HttpStatus.NO_CONTENT);
		} 
		else {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		}
	}

	@PostMapping("/addAutore")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiungi un autore al catalogo", description = "Inserire solo il nome e cognome dell'autore da aggiungere")
	public ResponseEntity<Autore> addAutore(@RequestBody Autore autore) {
		Autore salvato = autoreService.addAutore(autore);
		return new ResponseEntity<>(salvato, HttpStatus.OK);
	}

	@DeleteMapping("/deleteAutore/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella un autore", description = "Inserire un ID di un autore da cancellare")
	public ResponseEntity<String> deleteAutoreById(@PathVariable Long id) {
		Optional<Autore> find = autoreService.findAutoreById(id);
		if (!find.isEmpty()) {
			Autore delete = find.get();
			List<Libro> allLibri = libroService.findAllLibri();
			for (Libro libro : allLibri) {
				libro.deleteAllFromSet(delete);
			}
			libroService.deleteLibriNoAutori();
			autoreService.deleteAutoreById(id);
			return new ResponseEntity<>("Autore e libri corrispondenti cancellati", HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>("Autore non trovato", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateAutore/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Modifica e aggiorna un determinato autore", description = "Inserire un ID di un autore da modificare (sia nella description, sia nel request body) e i relativi parametri da modificare")
	public ResponseEntity<Autore> updateAutore(@PathVariable(required = true) Long id, @RequestBody Autore autore) {
		Autore save = autoreService.updateAutore(id, autore);
		return new ResponseEntity<>(save, HttpStatus.OK);
	}

}