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

import com.valhallaride.valhallaride.model.Tienda;
import com.valhallaride.valhallaride.service.TiendaService;

@RestController
@RequestMapping("/api/v1/tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @GetMapping
    public ResponseEntity<List<Tienda>> listar() {
        List<Tienda> tiendas = tiendaService.findAll();
        if (tiendas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tiendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tienda> buscar(@PathVariable Long id) {
        try {
            Tienda tienda = tiendaService.findById(id);
            return ResponseEntity.ok(tienda);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Tienda> guardar(@RequestBody Tienda tienda) {
        Tienda tiendaNueva = tiendaService.save(tienda);
        return ResponseEntity.status(HttpStatus.CREATED).body(tiendaNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tienda> actualizar(@PathVariable Long id, @RequestBody Tienda tienda) {
        try {
            tiendaService.save(tienda);
            return ResponseEntity.ok(tienda);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tienda> patchTienda(@PathVariable Long id, @RequestBody Tienda partialTienda) {
        try {
            Tienda updatedTienda = tiendaService.patchTienda(id, partialTienda);
            return ResponseEntity.ok(updatedTienda);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            tiendaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
