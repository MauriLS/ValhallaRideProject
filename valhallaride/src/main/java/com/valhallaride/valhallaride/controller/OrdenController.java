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

import com.valhallaride.valhallaride.model.Orden;
import com.valhallaride.valhallaride.service.OrdenService;

@RestController
@RequestMapping("/api/v1/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public ResponseEntity<List<Orden>> listar() {
        List<Orden> ordenes = ordenService.findAll();
        if (ordenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ordenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> buscar(@PathVariable Long id) {
        try {
            Orden orden = ordenService.findById(id);
            return ResponseEntity.ok(orden);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Orden> guardar(@RequestBody Orden orden) {
        Orden ordenNueva = ordenService.save(orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordenNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizar(@PathVariable Long id, @RequestBody Orden orden) {
        try {
            ordenService.save(orden);
            return ResponseEntity.ok(orden);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Orden> patchOrden(@PathVariable Long id, @RequestBody Orden partialOrden) {
        try {
            Orden updateOrden = ordenService.patchOrden(id, partialOrden);
            return ResponseEntity.ok(updateOrden);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            ordenService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
