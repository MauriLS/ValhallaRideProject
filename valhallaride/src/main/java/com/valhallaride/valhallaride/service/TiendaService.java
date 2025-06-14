package com.valhallaride.valhallaride.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valhallaride.valhallaride.model.Tienda;
import com.valhallaride.valhallaride.repository.TiendaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    public List<Tienda> findAll() {
        return tiendaRepository.findAll();
    }

    public Tienda findById(Long id) {
        return tiendaRepository.findById(id).orElse(null);
    }

    public Tienda save(Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    public void delete(Long id) {
        tiendaRepository.deleteById(id);
    }

    public Tienda updateTienda(Long id, Tienda tienda){
        Tienda tiendaToUpdate = tiendaRepository.findById(id).orElse(null);
        if (tiendaToUpdate != null) {
            tiendaToUpdate.setNombreTienda(tienda.getNombreTienda());
            tiendaToUpdate.setDireccionTienda(tienda.getDireccionTienda());
            return tiendaRepository.save(tiendaToUpdate);
        } else {
            return null;
        }
    }

    public Tienda patchTienda(long id, Tienda parcialTienda) {
        Optional<Tienda> tiendaOptional = tiendaRepository.findById(id);
        if (tiendaOptional.isPresent()) {

            Tienda tiendaToUpdate = tiendaOptional.get();

            if (parcialTienda.getNombreTienda() != null) {
                tiendaToUpdate.setNombreTienda(parcialTienda.getNombreTienda());
            }

            if (parcialTienda.getDireccionTienda() != null) {
                tiendaToUpdate.setDireccionTienda(parcialTienda.getDireccionTienda());
            }

            return tiendaRepository.save(tiendaToUpdate);
        } else {
            return null;
        }
    }
}

