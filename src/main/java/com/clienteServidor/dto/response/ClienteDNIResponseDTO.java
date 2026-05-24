package com.clienteServidor.dto.response;

public record ClienteDNIResponseDTO(
        String numeroDocumento,
        String nombre,
        String apellidoP,
        String apellidoM
) {
}