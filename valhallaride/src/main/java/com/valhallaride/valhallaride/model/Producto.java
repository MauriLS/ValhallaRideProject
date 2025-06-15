package com.valhallaride.valhallaride.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.Column;
import jakarta.persistence.Table;
@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idProducto;
    
    @Column (length = 30, nullable = false)
    private String nombreProducto;

    @Column (length = 100, nullable = false)
    private String descripcionProducto;

    @Column (nullable = false)
    private Integer precioProducto;
    
    @Column (nullable = false)
    private Integer stockProducto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTienda", nullable = false)
    private Tienda tienda;

}   
