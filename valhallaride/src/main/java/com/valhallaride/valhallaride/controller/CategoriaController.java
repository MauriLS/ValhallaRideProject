package com.valhallaride.valhallaride.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valhallaride.valhallaride.model.Categoria;
import com.valhallaride.valhallaride.service.CategoriaService;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

   @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = categoriaService.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        for (Categoria c : categorias) {
            if (c.getProductos() != null) {
                c.getProductos().forEach(p -> {
                    p.setCategoria(null);
                    if (p.getTienda() != null) {
                        p.getTienda().setProductos(null);
                    }
                });
            }
        }

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
        try {
            Categoria categoria = categoriaService.findById(id);
            if (categoria == null) {
                return ResponseEntity.notFound().build();
            }


            if (categoria.getProductos() != null) {
                categoria.getProductos().forEach(p -> {
                    p.setCategoria(null);
                    if (p.getTienda() != null) {
                        p.getTienda().setProductos(null);
                    }
                });
            }

            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Categoria> guardar(@RequestBody Categoria categoria) {
        Categoria categoriaNueva = categoriaService.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria catActualizada = categoriaService.updateCategoria(id, categoria);
        if (catActualizada != null){
            if (catActualizada.getProductos() != null){
                catActualizada.getProductos().forEach(p -> {
                    p.setCategoria(null);
                    if (p.getTienda() != null) {
                        p.getTienda().setProductos(null);
                    }
                });
            }
            return ResponseEntity.ok(catActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> patchCategoria(@PathVariable Long id, @RequestBody Categoria partialCategoria) {
        try {
            Categoria updatedCategoria = categoriaService.patchCategoria(id, partialCategoria);

            if (updatedCategoria.getProductos() != null){
                updatedCategoria.getProductos().forEach(p -> {
                    p.setCategoria(null);
                    if (p.getTienda() != null){
                        p.getTienda().setProductos(null);
                    }
                });
            }
            return ResponseEntity.ok(updatedCategoria);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            categoriaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
