package com.valhallaride.valhallaride.service;

import com.valhallaride.valhallaride.model.Orden;
import com.valhallaride.valhallaride.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    public Orden findById(Integer id) {
        return ordenRepository.findById(id).orElse(null);
    }

    public List<Orden> findByUsuario(Integer idUsuario) {
        return ordenRepository.findByUsuario_IdUsuario(idUsuario);
    }

    public List<Orden> findByPagado(Boolean pagado) {
        return ordenRepository.findByPagado(pagado);
    }

    public Orden save(Orden orden) {
        if (orden.getFecha() == null) {
            orden.setFecha(LocalDate.now());
        }
        if (orden.getPagado() == null) {
            orden.setPagado(false);
        }
        return ordenRepository.save(orden);
    }

    public Orden actualizarEstadoPago(Integer id, Boolean pagado) {
        Orden orden = ordenRepository.findById(id).orElse(null);
        if (orden != null) {
            orden.setPagado(pagado);
            return ordenRepository.save(orden);
        }
        return null;
    }

    public void delete(Integer id) {
        ordenRepository.deleteById(id);
    }
}
