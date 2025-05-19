package com.valhallaride.valhallaride.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valhallaride.valhallaride.model.Producto;
import com.valhallaride.valhallaride.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar(){
        List <Producto> productos = productoService.findAll();
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        for(Producto p : productos){
            if(p.getCategoria() != null){
                p.getCategoria().setProductos(null);
            }
            if(p.getTienda() != null){
                p.getTienda().setProductos(null);
            }
        }
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscar(@PathVariable Long id){
        try{
            Producto producto = productoService.findById(id);
            
            if (producto.getCategoria() != null){
                producto.getCategoria().setProductos(null);
            }
            if(producto.getTienda() != null){
                producto.getTienda().setProductos(null);
            }
            return ResponseEntity.ok(producto);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto){
        Producto productoNuevo = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto){
        try{
            productoService.save(producto);
            return ResponseEntity.ok(producto);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Producto> patchProducto(@PathVariable Long id, @RequestBody Producto partialProducto){
        try{
            Producto updatedProducto = productoService.patchProducto(id, partialProducto);

            if (updatedProducto.getCategoria() != null){
                updatedProducto.getCategoria().setProductos(null);
            }
            if (updatedProducto.getTienda() != null){
                updatedProducto.getTienda().setProductos(null);
            }
            
            return ResponseEntity.ok(updatedProducto);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
