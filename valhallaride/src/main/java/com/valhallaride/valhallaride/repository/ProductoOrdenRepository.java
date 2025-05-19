package com.valhallaride.valhallaride.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valhallaride.valhallaride.model.Producto;
import com.valhallaride.valhallaride.model.ProductoOrden;

import java.util.List;

public interface ProductoOrdenRepository extends JpaRepository<ProductoOrden, Long> {


    List<ProductoOrden> findByProducto_idProducto(Producto producto); // Busca todas las instancias de "ProductoOrden" que esten relacionadas con un Producto específico
    List<ProductoOrden> findByOrden_idOrden(Integer idOrden); // Busca todas las instancias de "ProductoOrden" que estén relacionadas con una Orden específica
    List<ProductoOrden> findByCantidad(Integer cantidad); // Busca todas las instancias de "ProductoOrden" que tengan una cantidad específica
}

