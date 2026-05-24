package com.clienteServidor.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_rol", unique = true)
    private String nombre;
}