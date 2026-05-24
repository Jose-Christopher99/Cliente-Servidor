package com.clienteServidor.repository;

import com.clienteServidor.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
