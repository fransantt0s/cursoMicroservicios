package com.fransantt0s.springboot.app.productos.controller;

import com.fransantt0s.springboot.app.productos.entity.Producto;
import com.fransantt0s.springboot.app.productos.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductoController {

    @Autowired
    private Environment env;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private IProductoService productoService;

    @GetMapping("/productos")
    public  ResponseEntity<List<Producto>> listarProductos(){
        List <Producto> productos = productoService.findAll();
       List<Producto> productosMap = productos.stream().map(p ->{
            p.setPort(Integer.parseInt(env.getProperty("local.server.port")));
//           p.setPort(port);
            return p;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(productosMap);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Producto> getProductoById(@PathVariable Long id){
        Producto producto = productoService.findById(id);
        producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
//        producto.setPort(port);
//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return ResponseEntity.ok(producto);

    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto){
        return new ResponseEntity<>(productoService.saveProduct(producto), HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) throws Exception {
        productoService.deleteByid(id);
       return ResponseEntity.ok(null);
    }

}
