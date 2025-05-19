package com.valhallaride.valhallaride.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valhallaride.valhallaride.model.Orden;
import com.valhallaride.valhallaride.repository.OrdenRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    public Orden findById(Integer id) {
        return ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + id));
    }

    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    public void delete(Integer id) {
        if (!ordenRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. Orden no encontrada con ID: " + id);
        }
        ordenRepository.deleteById(id);
    }

    public Orden updateOrden(Integer id, Orden orden) {
        Orden ordenToUpdate = findById(id); // ya lanza excepciÃ³n si no existe
        ordenToUpdate.setFecha(orden.getFecha());
        ordenToUpdate.setTotal(orden.getTotal());
        return ordenRepository.save(ordenToUpdate);
    }

    public Orden patchOrden(Integer id, Orden parcialOrden) {
        Orden ordenToUpdate = findById(id);

        if (parcialOrden.getFecha() != null) {
            ordenToUpdate.setFecha(parcialOrden.getFecha());
        }

        if (parcialOrden.getTotal() != null) {
            ordenToUpdate.setTotal(parcialOrden.getTotal());
        }

        return ordenRepository.save(ordenToUpdate);
    }
}
