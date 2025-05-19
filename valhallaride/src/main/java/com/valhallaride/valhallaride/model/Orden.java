package com.valhallaride.valhallaride.model;

import java.security.PrivateKey;
import java.sql.Date;

import org.hibernate.mapping.Join;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Orden")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrden;

    @Column(nullable = false)
    private Date fecha;

    @Column(length = 8, nullable = false)
    private Integer total;

    @OneToOne
    @JoinColumn(name = "id_met_pago", nullable = false)
    private MetodoPago metodoPago;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
