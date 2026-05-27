package com.clienteServidor.dto.response;

public record ClienteDNIResponseDTO(
     String document_number,
     String first_name,
     String first_last_name,
     String second_last_name
) {
}