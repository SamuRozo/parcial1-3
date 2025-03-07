package com.catalogo.libros.service;

import com.catalogo.libros.model.Autor;
import com.catalogo.libros.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> getAllAutores() {
        return autorRepository.findAll();
    }

    public Autor getAutorById(Long id) {
        return autorRepository.findById(id)
                .orElse(null);
    }

    public Autor createAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor updateAutor(Long id, Autor autorDetails) {
        Autor autor = getAutorById(id);
        if (autor != null) {
            autor.setNombre(autorDetails.getNombre());
            autor.setApellido(autorDetails.getApellido());
            autor.setFechaNacimiento(autorDetails.getFechaNacimiento());
            autor.setNacionalidad(autorDetails.getNacionalidad());
            return autorRepository.save(autor);
        }
        return null;
    }

    public boolean deleteAutor(Long id) {
        Autor autor = getAutorById(id);
        if (autor != null) {
            autorRepository.delete(autor);
            return true;
        }
        return false;
    }
}