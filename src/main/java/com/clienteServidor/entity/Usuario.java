package com.clienteServidor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass /* ESTA ES UNA ANOTACIÓN JPA QUE DICE QUE ESTA CLASE NO SE CREARA COMO UNA TABLA EN LA BASE DE DATOS
PERO HEREDARA SUS ATRIBUTOS CON SUS ANOTACIONES A SUS CLASES HIJAS*/
public abstract class Usuario {
    @Column(nullable = false, name = "tipo_doc", length = 10)
    private String tipoDocumento;
    @Column(nullable = false, name="nro_doc", length=12)
    private String nroDocumento;
    @Column(nullable = false, name="nombres")
    private String nombres;
    @Column(nullable = false, name="apellido_paterno")
    private String apellidoP;
    @Column(nullable = false, name="apellido_materno")
    private String apellidoM;
    @Column(name="correo", unique = true)
    private String correo;
    @Column(name="telefono", length = 9)
    private String telefono;
    @Column(name="direccion")
    private String direccion;
}