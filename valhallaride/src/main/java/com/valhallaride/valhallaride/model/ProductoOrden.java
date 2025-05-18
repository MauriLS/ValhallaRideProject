package com.valhallaride.valhallaride.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "productoOrden")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idProductoOrden;


    @Column(length = 5, nullable = false)
    private int cantidad;

    @OneToOne
    @JoinColumn(name = "idOrden", nullable = false)
    private Orden orden;

    @OneToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;
}
