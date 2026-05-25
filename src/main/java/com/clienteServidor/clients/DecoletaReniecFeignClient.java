package com.clienteServidor.clients;

import com.clienteServidor.dto.response.ClienteDNIResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "reniec-client", url="${decoleta.base-url}")
public interface DecoletaReniecFeignClient {

    @GetMapping("/v1/reniec/dni")
    ClienteDNIResponseDTO consultarDni(@RequestParam("numero") String numero,
                                       @RequestHeader("Authorization") String authorization);
}
