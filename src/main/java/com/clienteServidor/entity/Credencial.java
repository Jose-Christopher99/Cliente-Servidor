package com.clienteServidor.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="credenciales")
public class Credencial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Empleado empleado;
    @Column(name = "usuario", nullable = false, unique = true, length = 20)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
}
