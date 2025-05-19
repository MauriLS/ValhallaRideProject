package com.valhallaride.valhallaride.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valhallaride.valhallaride.model.Producto;
import com.valhallaride.valhallaride.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    public Producto updateProducto(Long id, Producto producto){
        Producto productoToUpdate = productoRepository.findById(id).orElse(null);
        if (productoToUpdate != null){
            productoToUpdate.setNombreProducto(producto.getNombreProducto());
            productoToUpdate.setDescripcionProducto(producto.getDescripcionProducto());
            productoToUpdate.setPrecioProducto(producto.getPrecioProducto());
            productoToUpdate.setPrecioProducto(producto.getPrecioProducto());
            return productoRepository.save(productoToUpdate);
        } else {
            return null;
        }
    }


    public Producto patchProducto(Long id, Producto parcialProducto) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {

            Producto productoToUpdate = productoOptional.get();

            if (parcialProducto.getNombreProducto() != null) {
                productoToUpdate.setNombreProducto(parcialProducto.getNombreProducto());
            }

            if (parcialProducto.getDescripcionProducto() != null) {
                productoToUpdate.setDescripcionProducto(parcialProducto.getDescripcionProducto());
            }

            if (parcialProducto.getPrecioProducto() != null) {
                productoToUpdate.setPrecioProducto(parcialProducto.getPrecioProducto());
            }

            if (parcialProducto.getStockProducto() != null) {
                productoToUpdate.setStockProducto(parcialProducto.getStockProducto());
            }

            return productoRepository.save(productoToUpdate);
        } else {
            return null;
        }
    }

}

