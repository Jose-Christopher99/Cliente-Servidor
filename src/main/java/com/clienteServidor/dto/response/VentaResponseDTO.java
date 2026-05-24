package com.clienteServidor.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record VentaResponseDTO(
        Long idVenta,
        String nombreCliente,
        BigDecimal total,
        String estado,
        String numeroComprobante,
        String tipoComprobante,
        LocalDate fechaEmision,
        LocalTime horaEmision
) {
}