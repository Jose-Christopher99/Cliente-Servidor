package com.clienteServidor.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClienteDNIResponseDTO(
        @JsonProperty("document_number")
        String numeroDocumento,
        @JsonProperty("first_name")
        String nombre,
        @JsonProperty("first_last_name")
        String apellidoP,
        @JsonProperty("second_last_name")
        String apellidoM
) {
}