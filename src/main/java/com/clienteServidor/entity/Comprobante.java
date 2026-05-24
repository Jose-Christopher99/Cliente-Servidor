package com.clienteServidor.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name="comprobantes")
public class Comprobante {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "id_comprobante")
    private Long id;
    @OneToOne
    @JoinColumn(name="id_venta",unique = true, nullable = false)
    private Venta venta;
    @Column(name = "tipo",nullable = false)
    private String tipo; //ESTE ATRIBUTO ES PARA IDENTIFICAR SI ES BOLETA O FACTURA
    @Column(name="nro_comprobante", nullable = false)
    private String numeroComprobante;
    @Column(name="fecha")
    private LocalDate fechaEmision;
    @Column(name="hora")
    private LocalTime horaEmision;
}
