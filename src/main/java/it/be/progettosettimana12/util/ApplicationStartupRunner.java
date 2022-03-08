package it.be.progettosettimana12.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.be.progettosettimana12.model.Autore;
import it.be.progettosettimana12.model.Categoria;
import it.be.progettosettimana12.model.Libro;
import it.be.progettosettimana12.repository.AutoreRepository;
import it.be.progettosettimana12.repository.CategoriaRepository;
import it.be.progettosettimana12.repository.LibroRepository;

@Component

public class ApplicationStartupRunner implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private AutoreRepository autoreRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public void run(String... args) throws Exception {

		Autore autore = new Autore();
		autore.setNome("Maria");
		autore.setCognome("Lerario");
		autoreRepository.save(autore);

		Autore autore1 = new Autore();
		autore1.setNome("Catia");
		autore1.setCognome("Cat");
		autoreRepository.save(autore1);

		Autore autore2 = new Autore();
		autore2.setNome("Graziano");
		autore2.setCognome("Roma");
		autoreRepository.save(autore2);
		
		Autore autore3 = new Autore();
		autore3.setNome("Pasqua");
		autore3.setCognome("Pasquetta");
		autoreRepository.save(autore3);
		
		Categoria categoria = new Categoria();
		categoria.setNome("Autobiografia");
		categoriaRepository.save(categoria);

		Categoria categoria1 = new Categoria();
		categoria1.setNome("Romanzo");
		categoriaRepository.save(categoria1);

		Categoria categoria2 = new Categoria();
		categoria2.setNome("Fantascienza");
		categoriaRepository.save(categoria2);

		Libro libro = new Libro();
		libro.setTitolo("Nuova vita");
		libro.setAnnoPubblicazione(1999);
		libro.setPrezzo(39.99);

		Libro libro1 = new Libro();
		libro1.setTitolo("amore a prima vista");
		libro1.setAnnoPubblicazione(2000);
		libro1.setPrezzo(29.99);

		Libro libro2 = new Libro();
		libro2.setTitolo("Amazone");
		libro2.setAnnoPubblicazione(2003);
		libro2.setPrezzo(19.99);
		
		Libro libro3 = new Libro();
		libro3.setTitolo("Nuvola");
		libro3.setAnnoPubblicazione(2001);
		libro3.setPrezzo(22.99);
		
		libro.getAutori().add(autore);
		libro.getCategorie().add(categoria);
		libroRepository.save(libro);

		libro1.getAutori().add(autore1);
		libro1.getCategorie().add(categoria1);
		libroRepository.save(libro1);

		libro2.getAutori().add(autore2);
		libro2.getCategorie().add(categoria2);
		libroRepository.save(libro2);
		
		libro3.getAutori().add(autore3);
		libro3.getCategorie().add(categoria1);
		libroRepository.save(libro3);
	}

}