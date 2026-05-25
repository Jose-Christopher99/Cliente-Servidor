package com.clienteServidor.service;

import com.clienteServidor.dto.response.ProductoResponseDTO;
import com.clienteServidor.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<ProductoResponseDTO> listar(){
        return repo.findAll()
                .stream()
                .map(p-> new ProductoResponseDTO(
                        p.getIdProducto(),
                        p.getNombre(),
                        p.getPrecio(),
                        p.getStock()
                ))
                .toList();
    }
}
