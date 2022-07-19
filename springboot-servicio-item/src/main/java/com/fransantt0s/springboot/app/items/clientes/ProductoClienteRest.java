package com.fransantt0s.springboot.app.items.clientes;

import com.fransantt0s.springboot.app.items.entity.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

    @GetMapping("/productos")
    public List<Producto> listarProductos();

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id);

}