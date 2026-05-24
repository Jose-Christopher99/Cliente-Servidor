package com.clienteServidor.dto.request;

public record DetalleVentaRequestDTO(
        Long productoId,
        Integer cantidad
) {
}