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
import it.be.progettosettimana12.model.Libro;
import it.be.progettosettimana12.service.LibroService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")
public class LibroController {

	@Autowired
	private LibroService libroService;

	@GetMapping("/findallLibri")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutti i libri disponibili")
	public ResponseEntity<List<Libro>> findAllLibri() {
		List<Libro> findAll = libroService.findAllLibri();
		if (!findAll.isEmpty()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/getLibro/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova un determinato libro tramite il suo id", description = "Inserire un ID di un libro presente nel database")
	public ResponseEntity<Libro> findLibroById(@PathVariable(required = true) Long id) {
		Optional<Libro> find = libroService.findLibroById(id);
		if (find.isEmpty()) {
			return new ResponseEntity<>(find.get(), HttpStatus.NO_CONTENT);
		} 
		else {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		}
	}

	@PostMapping("/addLibro")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiungi un libro al catalogo", description = "Inserire i parametri di un libro da aggiungere, tranne il suo ID. Vanno poi inseriti solo gli ID di autore e categoria, se già presenti nel catalogo, altrimenti sono da creare nei rispettivi metodi")
	public ResponseEntity<Libro> addLibro(@RequestBody Libro libro) {
		Libro salvato = libroService.addLibro(libro);
		return new ResponseEntity<>(salvato, HttpStatus.OK);
	}

	@DeleteMapping("/deleteLibro/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella un libro", description = "Inserire un ID di un libro da cancellare")
	public ResponseEntity<String> deleteLibroById(@PathVariable Long id) {
		libroService.deleteLibroById(id);
		return new ResponseEntity<>("Libro cancellato", HttpStatus.OK);
	}

	@PutMapping("/updateLibro/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Modifica e aggiorna un determinato libro", description = "Inserire un ID di un libro da modificare (sia nella description, sia nel request body). Vanno poi inseriti solo gli ID di autore e categoria, se già presenti nel catalogo, altrimenti sono da creare nei rispettivi metodi")
	public ResponseEntity<Libro> updateLibro(@PathVariable(required = true) Long id, @RequestBody Libro libro) {
		Libro save = libroService.updateLibro(id, libro);
		return new ResponseEntity<>(save, HttpStatus.OK);
	}

}