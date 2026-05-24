package com.clienteServidor.repository;

import com.clienteServidor.entity.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
}
