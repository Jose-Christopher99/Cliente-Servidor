package com.clienteServidor.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) //NECESARIO POR LOMBOOK AL USAR HERENCIA
@Entity
@Table(name = "empleados")
public class Empleado extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @ManyToOne
    @JoinColumn(name= "rol_id", nullable = false)
    private Roles rol;
}