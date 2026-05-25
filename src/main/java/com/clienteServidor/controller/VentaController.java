package com.clienteServidor.controller;

import com.clienteServidor.dto.request.ProcesarVentaRequestDTO;
import com.clienteServidor.dto.response.VentaResponseDTO;
import com.clienteServidor.entity.Empleado;
import com.clienteServidor.repository.EmpleadoRepository;
import com.clienteServidor.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/ventas")
public class VentaController {
    private final VentaService ventaService;
    private final EmpleadoRepository repo;

    public VentaController(VentaService ventaService, EmpleadoRepository repo) {
        this.ventaService = ventaService;
        this.repo = repo;
    }

    @PostMapping("/procesar")
    public ResponseEntity<VentaResponseDTO> procesarVenta(@RequestBody ProcesarVentaRequestDTO dto){
        Empleado empleado= repo.findById(dto.venta().empleadoId()).get();
        VentaResponseDTO response = ventaService.procesarVenta(
                dto.venta(),
                dto.cliente(),
                empleado
        );
        return ResponseEntity.ok(response);
    }
}
