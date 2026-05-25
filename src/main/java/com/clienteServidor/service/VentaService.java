package com.clienteServidor.service;

import com.clienteServidor.repository.ComprobanteRepository;
import com.clienteServidor.repository.DetalleVentaRepository;
import com.clienteServidor.repository.ProductoRepository;
import com.clienteServidor.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VentaService {
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ComprobanteRepository comprobanteRepository;
    private final ClienteService clienteService;
    @Value("${stripe.secret-key}")
    private String secretKey;

    public VentaService(VentaRepository ventaRepository, ProductoRepository productoRepository, DetalleVentaRepository detalleVentaRepository, ComprobanteRepository comprobanteRepository, ClienteService clienteService) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.comprobanteRepository = comprobanteRepository;
        this.clienteService = clienteService;
    }
}
