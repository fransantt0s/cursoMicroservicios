package com.fransantt0s.springboot.app.items.service.impl;

import com.fransantt0s.springboot.app.items.clientes.ProductoClienteRest;
import com.fransantt0s.springboot.app.items.entity.Item;
import com.fransantt0s.springboot.app.items.entity.Producto;
import com.fransantt0s.springboot.app.items.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
//@Primary
public class ItemServiceFeignImpl implements ItemService {

    @Autowired
    ProductoClienteRest productoClienteRest;

    @Override
    public List<Item> findAll() {
        return productoClienteRest.listarProductos().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Producto producto = productoClienteRest.getProductoById(id);
        return new Item(producto,cantidad);
    }
}
