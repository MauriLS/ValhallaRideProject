package com.valhallaride.valhallaride.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valhallaride.valhallaride.model.Categoria;
import com.valhallaride.valhallaride.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria updateCategoria(Long id, Categoria categoria){
        Categoria categoriaToUpdate = categoriaRepository.findById(id).orElse(null);
        if (categoriaToUpdate != null) {
            categoriaToUpdate.setNombreCategoria(categoria.getNombreCategoria());
            return categoriaRepository.save(categoriaToUpdate);
        } else {
            return null;
        }
    }
    

    public Categoria patchCategoria(Long id, Categoria parcialCategoria) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {

            Categoria categoriaToUpdate = categoriaOptional.get();

            if (parcialCategoria.getNombreCategoria() != null) {
                categoriaToUpdate.setNombreCategoria(parcialCategoria.getNombreCategoria());
            }

            return categoriaRepository.save(categoriaToUpdate);
        } else {
            return null;
        }
    }
}
