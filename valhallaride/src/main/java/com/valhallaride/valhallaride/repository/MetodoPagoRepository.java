package com.valhallaride.valhallaride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valhallaride.valhallaride.model.MetodoPago;
import java.util.List;


public interface MetodoPagoRepository extends JpaRepository <MetodoPago, Long>{

    List<MetodoPago> findByNomMetPago(String nomMetPago); // Chicos, aqui usamos List porque puede haber varios registros en la base de datos que tengan el mismo "nombreMetodo"
}
