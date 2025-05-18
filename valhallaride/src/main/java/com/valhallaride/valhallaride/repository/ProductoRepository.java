package com.valhallaride.valhallaride.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.valhallaride.valhallaride.model.Producto;
import java.util.List;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreProducto(String nombreProducto); // Encuentra productos por su nombre
    List<Producto> findByDescripcionProducto(String descripcionProducto); // Encuentra productos por su descripcion
    List<Producto> findByPrecioProducto(Integer precioProducto); // Encuentra productos por su precio
    List<Producto> findByStockProducto(Integer stockProducto); // Encuentra productos por su stock
    List<Producto> findByCategoria_idCategoria(Integer idCategoria); // Encuentra productos que pertenezcan a una categoria especficia(clave primaria de "Categoria")
    List<Producto> findByTienda_idTienda(Integer id_tienda); // Encuentra productos que pertenezcan a una tienda especifica(clave primaria de "Tienda")
}
