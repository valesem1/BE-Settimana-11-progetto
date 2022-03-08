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
import it.be.progettosettimana12.model.Categoria;
import it.be.progettosettimana12.model.Libro;
import it.be.progettosettimana12.service.CategoriaService;
import it.be.progettosettimana12.service.LibroService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api")

public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private LibroService libroService;

	@GetMapping("/findallCategorie")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova tutte le categorie disponibili")
	public ResponseEntity<List<Categoria>> findAllCategorie() {
		List<Categoria> findAll = categoriaService.findAllCategorie();
		if (!findAll.isEmpty()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/getCategoria/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@Operation(summary = "Trova una determinata categoria tramite il suo id", description = "Inserire un ID di una categoria presente nel database")
	public ResponseEntity<Categoria> findCategoriaById(@PathVariable(required = true) Long id) {
		Optional<Categoria> find = categoriaService.findCategoriaById(id);
		if (find.isEmpty()) {
			return new ResponseEntity<>(find.get(), HttpStatus.NO_CONTENT);
		} 
		else {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		}
	}

	@PostMapping("/addCategoria")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Aggiungi una categoria al catalogo", description = "Inserire solo il nome di una categoria da aggiungere")
	public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria categoria) {
		Categoria salvata = categoriaService.addCategoria(categoria);
		return new ResponseEntity<>(salvata, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCategoria/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Cancella una categoria", description = "Inserire un ID di una categoria da cancellare")
	public ResponseEntity<String> deleteCategoriaById(@PathVariable Long id) {
		Optional<Categoria> find = categoriaService.findCategoriaById(id);
		if (!find.isEmpty()) {
			Categoria delete = find.get();
			List<Libro> allLibri = libroService.findAllLibri();
			for (Libro libro : allLibri) {
				libro.deleteAllFromSet(delete);
			}
			categoriaService.deleteCategoriaById(id);
			return new ResponseEntity<>("Categoria cancellata", HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<>("Categoria non trovata", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateCategoria/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary = "Modifica e aggiorna una determinata categoria", description = "Inserire un ID di una categoria da modificare (sia nella description, sia nel request body) e modificare il nome")
	public ResponseEntity<Categoria> updateCategoria(@PathVariable(required = true) Long id,
			@RequestBody Categoria categoria) {
		Categoria save = categoriaService.updateCategoria(id, categoria);
		return new ResponseEntity<>(save, HttpStatus.OK);
	}

}