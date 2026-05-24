package com.clienteServidor.dto.request;

import java.util.List;

public record VentaRequestDTO(
        String tipoDocumento,
        String nroDocumento,
        Long empleadoId,
        List<DetalleVentaRequestDTO> productos,
        String tipoComprobante //SI ES BOLETA O FACTURA
) {
}