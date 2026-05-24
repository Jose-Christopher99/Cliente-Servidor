package com.clienteServidor.service;

import com.clienteServidor.repository.RolesRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository){
        this.rolesRepository=rolesRepository;
    }
}
