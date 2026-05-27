package com.clienteServidor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name="detalle_venta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;
    @ManyToOne
    @JoinColumn(name = "orden_id",nullable = false)
    private Venta venta;
    @ManyToOne
    @JoinColumn(name="producto_id", nullable = false)
    private Producto productos;
    @Column(name="cantidad", nullable = false)
    private Integer cantidad;
    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;
    @Column(name="subTotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;
}
