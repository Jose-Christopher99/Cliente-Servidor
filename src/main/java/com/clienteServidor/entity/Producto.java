package com.clienteServidor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @Column(name="nombre", nullable = false)
    private String nombre;
    @Column(name="precio",precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;
    @Column(name="stock", nullable = false)
    private Integer stock;
    @Column(name = "categoria")
    private String categoria;
}
