package com.valhallaride.valhallaride.service;

import java.util.List;
import java.util.Optional;

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

    public Orden findById(Long id) {
        return ordenRepository.getById(id);
    }

    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    public void delete(Long id) {
        ordenRepository.deleteById(id);
    }

    public Orden updateOrden(Long id, Orden orden){
        Orden ordenToUpdate = ordenRepository.findById(id).orElse(null);
        if (ordenToUpdate != null) {
            ordenToUpdate.setFecha(orden.getFecha());
            ordenToUpdate.setTotal(orden.getTotal());
            return ordenRepository.save(ordenToUpdate);
        } else {
            return null;
        }
    }

    public Orden patchOrden(long id, Orden parcialOrden) {
        Optional<Orden> ordenOptional = ordenRepository.findById(id);
        if (ordenOptional.isPresent()) {

            Orden ordenToUpdate = ordenOptional.get();

            if (parcialOrden.getFecha() != null) {
                ordenToUpdate.setFecha(parcialOrden.getFecha());
            }

            if (parcialOrden.getTotal() != null) {
                ordenToUpdate.setTotal(parcialOrden.getTotal());
            }

            return ordenRepository.save(ordenToUpdate);
        } else {
            return null;
        }

    }
}