package com.clienteServidor.dto.request;

public record ClienteRequestDTO(
        String tipoDocumento,
        String nroDocumento,
        String nombres,
        String apellidoP,
        String apellidoM,
        String direccion,
        String telefono
) {
}