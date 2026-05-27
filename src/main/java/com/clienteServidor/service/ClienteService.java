package com.clienteServidor.service;

import com.clienteServidor.clients.DecoletaReniecFeignClient;
import com.clienteServidor.dto.request.ClienteRequestDTO;
import com.clienteServidor.dto.response.ClienteDNIResponseDTO;
import com.clienteServidor.entity.Cliente;
import com.clienteServidor.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    private final ClienteRepository repo;
    private final DecoletaReniecFeignClient feignClient;
    @Value("${decoleta.token}")
    private String token;

    public ClienteService(ClienteRepository repo, DecoletaReniecFeignClient feignClient) {
        this.repo = repo;
        this.feignClient = feignClient;
    }

    public ClienteDNIResponseDTO consultarApi(String numero){
        String auth = "Bearer "+token;
        ClienteDNIResponseDTO dto= feignClient.consultarDni(numero, auth);
        return dto;
    }

    @Transactional
    public Cliente consultarYGuardar(ClienteRequestDTO dto){
        return repo.findByNroDocumento(dto.nroDocumento())
                .orElseGet(()->{
                    Cliente cliente= new Cliente();
                    cliente.setTipoDocumento(dto.tipoDocumento());
                    cliente.setNroDocumento(dto.nroDocumento());
                    cliente.setNombres(dto.nombres());
                    cliente.setApellidoP(dto.apellidoP());
                    cliente.setApellidoM(dto.apellidoM());
                    cliente.setDireccion(dto.direccion());
                    cliente.setTelefono(dto.telefono());
                    return repo.save(cliente);
                });
    }
}