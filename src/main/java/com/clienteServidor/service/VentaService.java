package com.clienteServidor.service;

import com.clienteServidor.dto.request.ClienteRequestDTO;
import com.clienteServidor.dto.request.DetalleVentaRequestDTO;
import com.clienteServidor.dto.request.VentaRequestDTO;
import com.clienteServidor.dto.response.VentaResponseDTO;
import com.clienteServidor.entity.*;
import com.clienteServidor.repository.ComprobanteRepository;
import com.clienteServidor.repository.DetalleVentaRepository;
import com.clienteServidor.repository.ProductoRepository;
import com.clienteServidor.repository.VentaRepository;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public VentaResponseDTO procesarVenta(VentaRequestDTO request, ClienteRequestDTO clienteDTO, Empleado empleado){
        //Guardamos al cliente si no existe o lo buscamos en si existe
        Cliente cliente= clienteService.consultarYGuardar(clienteDTO);
        //Creamos la venta
        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setEmpleado(empleado);
        venta.setFecha_venta(LocalDate.now());
        venta.setHora_venta(LocalTime.now());
        venta.setEstado("PENDIENTE");
        venta.setTotal(BigDecimal.ZERO);
        //Guardamos la venta en la BD
        ventaRepository.save(venta);
        //Ahora guardaremos el detalle de la venta y calculamos el total
        BigDecimal total= BigDecimal.ZERO; //Es simplemente el valor de cero
        List<DetalleVenta> detalles= new ArrayList();
        for(DetalleVentaRequestDTO item: request.productos()){
            Producto producto = productoRepository.findById(item.productoId()).get();
            //Verificamos el stock del producto
            if(producto.getStock() < item.cantidad()){
                throw new RuntimeException("Stock insuficiente para: "+ producto.getNombre());
            }
            BigDecimal subTotal= producto.getPrecio().multiply(BigDecimal.valueOf(item.cantidad()));//El multiply multiplica dos BigDecimal
            DetalleVenta detalle= new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProductos(producto);
            detalle.setCantidad(item.cantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubTotal(subTotal);
            detalles.add(detalle);

            //Descontamos el stock de los productos adquiridos
            producto.setStock(producto.getStock()-item.cantidad());
            productoRepository.save(producto);
            total = total.add(subTotal);// El add va sumando cada subtotal
        }
        detalleVentaRepository.saveAll(detalles);//Un solo insert para todos

        //Actualizamos el total en la venta
        venta.setTotal(total);
        ventaRepository.save(venta);
        //Procesamos el pago con pasarela de pagos con STRIPE
        try{
            Stripe.apiKey= secretKey;
            PaymentIntentCreateParams params= PaymentIntentCreateParams.builder()
                    .setAmount(total.multiply(BigDecimal.valueOf(100)).longValue()) //Porque no trabaja con decimales, por eso lo convertimos a centavos
                    .setCurrency("pen") //Moneda del Peru
                    .setDescription("Venta #"+ venta.getId())
                    .build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);
            //Actualizamos el estado de la venta
            venta.setEstado("PAGADO");
            ventaRepository.save(venta);
            //Generamos comprobante de pago
            Comprobante comprobante= new Comprobante();
            comprobante.setVenta(venta);
            comprobante.setTipo(request.tipoComprobante());
            comprobante.setNumeroComprobante(generarComprobante(request.tipoComprobante()));
            comprobante.setFechaEmision(LocalDate.now());
            comprobante.setHoraEmision(LocalTime.now());
            comprobanteRepository.save(comprobante);

            return new VentaResponseDTO(
                    venta.getId(),
                    clienteDTO.nombres() + " "+ clienteDTO.apellidoP(),
                    total,
                    "PAGADO",
                    comprobante.getNumeroComprobante(),
                    request.tipoComprobante(),
                    comprobante.getFechaEmision(),
                    comprobante.getHoraEmision()
            );
        }catch (Exception e){
            venta.setEstado("CANCELADO");
            ventaRepository.save(venta);
            throw new RuntimeException("Error al procesar el pago: "+ e.getMessage());
        }
    }
    //Metodo privado de uso exclusivo
    private String generarComprobante(String tipo){
        String prefijo = tipo.equals("BOLETA") ? "B001" : "F001";
        long conteo = comprobanteRepository.count() + 1;
        return String.format("%s-%05d", prefijo,conteo);
    }
}
