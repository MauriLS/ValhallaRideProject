package com.valhallaride.valhallaride.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valhallaride.valhallaride.model.Tienda;

public interface TiendaRepository extends JpaRepository<Tienda, Long> {

    Optional<Tienda> findByNombreTienda(String nombreTienda);

    List<Tienda> findByDireccionTienda(String direccionTienda);

}
