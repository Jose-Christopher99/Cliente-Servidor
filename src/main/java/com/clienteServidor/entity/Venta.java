package com.clienteServidor.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "orden_venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="id_empleado", nullable = false)
    private Empleado empleado;
    @Column(name="fecha")
    private LocalDate fecha_venta;
    @Column(name = "hora")
    private LocalTime hora_venta;
    @Column(name = "precio_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
    @Column(name="estado_pago", nullable = false)
    private String estado;
}
