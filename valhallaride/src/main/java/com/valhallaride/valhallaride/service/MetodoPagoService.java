package com.valhallaride.valhallaride.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valhallaride.valhallaride.model.MetodoPago;
import com.valhallaride.valhallaride.repository.MetodoPagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public List<MetodoPago> findAll() {
        return metodoPagoRepository.findAll();
    }

    public MetodoPago findById(Long id) {
        return metodoPagoRepository.getById(id);
    }

    public MetodoPago save(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public void delete(long id) {
        metodoPagoRepository.deleteById(id);
    }

    public MetodoPago patchMetodoPago(Long id, MetodoPago parcialMetodoPago) {
        Optional<MetodoPago> metodoPagOptional = metodoPagoRepository.findById(id);
        if (metodoPagOptional.isPresent()) {

            MetodoPago metodoPagoUpdate = metodoPagOptional.get();

            if (parcialMetodoPago.getNomMetPago() != null) {
                metodoPagoUpdate.setNomMetPago(parcialMetodoPago.getNomMetPago());
            }

            return metodoPagoRepository.save(metodoPagoUpdate);
        } else {
            return null;
        }
    }
}
