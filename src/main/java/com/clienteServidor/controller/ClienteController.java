package com.clienteServidor.controller;

import com.clienteServidor.dto.response.ClienteDNIResponseDTO;
import com.clienteServidor.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<ClienteDNIResponseDTO> buscarCliente(@RequestParam("dni") String dni){
        ClienteDNIResponseDTO response = clienteService.consultarApi(dni);
        return ResponseEntity.ok(response);
    }
}
