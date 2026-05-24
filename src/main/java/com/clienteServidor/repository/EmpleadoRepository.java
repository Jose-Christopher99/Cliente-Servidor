package com.clienteServidor.repository;

import com.clienteServidor.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
