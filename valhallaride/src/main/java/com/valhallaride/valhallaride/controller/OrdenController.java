package com.valhallaride.valhallaride.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.valhallaride.valhallaride.model.Orden;
import com.valhallaride.valhallaride.service.OrdenService;

@RestController
@RequestMapping("/api/v1/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    private void limpiarReferencias(Orden orden) {
        if (orden.getProductosOrden() != null) {
            orden.getProductosOrden().forEach(po -> {
                if (po != null) {
                    po.setOrden(null);
                }
            });
        }
        if (orden.getUsuario() != null) {
            orden.getUsuario().setOrdenes(null);
        }

    }

    @GetMapping
    public ResponseEntity<List<Orden>> listar() {
        List<Orden> ordenes = ordenService.findAll();

        if (ordenes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        ordenes.forEach(this::limpiarReferencias);
        return ResponseEntity.ok(ordenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> buscar(@PathVariable Integer id) {
        try {
            Orden orden = ordenService.findById(id);
            limpiarReferencias(orden);
            return ResponseEntity.ok(orden);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Orden> guardar(@RequestBody Orden orden) {
        Orden nuevaOrden = ordenService.save(orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOrden);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizar(@PathVariable Integer id, @RequestBody Orden orden) {
        try {
            orden.setIdOrden(id);
            Orden actualizada = ordenService.updateOrden(id, orden);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Orden> patchOrden(@PathVariable Integer id, @RequestBody Orden partialOrden) {
        try {
            Orden actualizada = ordenService.patchOrden(id, partialOrden);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            ordenService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
