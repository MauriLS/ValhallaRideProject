package com.valhallaride.valhallaride.model;

import jakarta.persistence.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tienda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTienda;

    @Column(length = 30, nullable = false, unique = true)
    private String nombreTienda;

    @Column(length = 30, nullable = false)
    private String direccionTienda;

    @OneToMany(mappedBy = "tienda")
    @JsonIgnore
    private List<Producto> productos;

}
