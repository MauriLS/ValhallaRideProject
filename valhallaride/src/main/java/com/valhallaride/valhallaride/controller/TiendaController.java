package com.valhallaride.valhallaride.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        for (Tienda tienda : tiendas) {
            if (tienda.getProductos() != null) {
                tienda.getProductos().forEach(p -> {
                    p.setTienda(null);
                    if (p.getCategoria() != null) {
                        p.getCategoria().setProductos(null);
                    }
                });
            }
        }

        return ResponseEntity.ok(tiendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tienda> buscar(@PathVariable Long id) {
        try {
            Tienda tienda = tiendaService.findById(id);

            if (tienda.getProductos() != null) {
                tienda.getProductos().forEach(p -> {
                    p.setTienda(null);
                    if (p.getCategoria() != null) {
                        p.getCategoria().setProductos(null);
                    }
                });
            }

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
        Tienda tiendaActualizada = tiendaService.updateTienda(id, tienda);
        if (tiendaActualizada != null) {
            if (tiendaActualizada.getProductos() != null) {
                tiendaActualizada.getProductos().forEach(p -> {
                    p.setTienda(null);
                    if (p.getCategoria() != null) {
                        p.getCategoria().setProductos(null);
                    }
                });
            }
            return ResponseEntity.ok(tiendaActualizada);
        } else {
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
