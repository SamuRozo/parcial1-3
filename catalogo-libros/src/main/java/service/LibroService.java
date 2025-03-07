package com.catalogo.libros.service;

import com.catalogo.libros.model.Libro;
import com.catalogo.libros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    public Libro getLibroById(Long id) {
        return libroRepository.findById(id)
                .orElse(null);
    }

    public List<Libro> getLibrosByAutorId(Long autorId) {
        return libroRepository.findByAutorId(autorId);
    }

    public Libro createLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro updateLibro(Long id, Libro libroDetails) {
        Libro libro = getLibroById(id);
        if (libro != null) {
            libro.setTitulo(libroDetails.getTitulo());
            libro.setIsbn(libroDetails.getIsbn());
            libro.setAnioPublicacion(libroDetails.getAnioPublicacion());
            libro.setGenero(libroDetails.getGenero());
            libro.setEditorial(libroDetails.getEditorial());
            libro.setDisponible(libroDetails.getDisponible());
            libro.setAutor(libroDetails.getAutor());
            return libroRepository.save(libro);
        }
        return null;
    }

    public boolean deleteLibro(Long id) {
        Libro libro = getLibroById(id);
        if (libro != null) {
            libroRepository.delete(libro);
            return true;
        }
        return false;
    }
}