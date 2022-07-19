package com.fransantt0s.springboot.app.items.controller;

import com.fransantt0s.springboot.app.items.entity.Item;
import com.fransantt0s.springboot.app.items.entity.Producto;
import com.fransantt0s.springboot.app.items.service.ItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("serviceFeign")
    public ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity <List<Item>> getAllItems(){
        List <Item> items = itemService.findAll();
        return ResponseEntity.ok(items);
    }

    @HystrixCommand(fallbackMethod ="metodoAlternativo")
    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public ResponseEntity <Item> findItemById(@PathVariable Long id,@PathVariable Integer cantidad){
        Item item = itemService.findById(id,cantidad);
        return ResponseEntity.ok(item);
    }

    public ResponseEntity<Item> metodoAlternativo(Long id, Integer cantidad){
        Item item = new Item();
        Producto producto = new Producto();

        item.setCantidad(cantidad);
        producto.setId(id);
        producto.setNombre("Camara Sony");
        producto.setPrecio(500D);
        item.setProducto(producto);
        return ResponseEntity.ok(item);
    }



}
