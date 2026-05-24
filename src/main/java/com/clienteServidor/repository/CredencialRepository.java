package com.clienteServidor.repository;

import com.clienteServidor.entity.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredencialRepository extends JpaRepository<Credencial,Long> {
}
