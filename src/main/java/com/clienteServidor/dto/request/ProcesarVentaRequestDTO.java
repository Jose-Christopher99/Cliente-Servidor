package com.clienteServidor.dto.request;

public record ProcesarVentaRequestDTO(
        VentaRequestDTO venta,
        ClienteRequestDTO cliente
) {
}