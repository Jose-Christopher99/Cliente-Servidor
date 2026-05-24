package com.clienteServidor.dto.response;

import java.math.BigDecimal;

public record ProductoResponseDTO(
        Long id,
        String nombre,
        BigDecimal precio,
        Integer stock
) {
}