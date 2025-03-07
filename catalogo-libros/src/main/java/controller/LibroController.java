package com.catalogo.libros.controller;

import com.catalogo.libros.model.Libro;
import com.catalogo.libros.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> libros = libroService.getAllLibros();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Libro libro = libroService.getLibroById(id);
        if (libro != null) {
            return new ResponseEntity<>(libro, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<Libro>> getLibrosByAutorId(@PathVariable Long autorId) {
        List<Libro> libros = libroService.getLibrosByAutorId(autorId);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Libro> createLibro(@Valid @RequestBody Libro libro) {
        Libro newLibro = libroService.createLibro(libro);
        return new ResponseEntity<>(newLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @Valid @RequestBody Libro libro) {
        Libro updatedLibro = libroService.updateLibro(id, libro);
        if (updatedLibro != null) {
            return new ResponseEntity<>(updatedLibro, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        boolean deleted = libroService.deleteLibro(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}