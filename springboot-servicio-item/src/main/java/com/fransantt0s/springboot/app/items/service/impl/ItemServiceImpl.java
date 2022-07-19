package com.fransantt0s.springboot.app.items.service.impl;

import com.fransantt0s.springboot.app.items.AppConfig;
import com.fransantt0s.springboot.app.items.entity.Item;
import com.fransantt0s.springboot.app.items.entity.Producto;
import com.fransantt0s.springboot.app.items.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("restTemplate")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate clienteRest;


        @Override
        public List<Item> findAll() {
            List <Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/productos",Producto[].class));
            return productos.stream().map(p -> new Item(p,1)).collect(Collectors.toList());
        }

        @Override
        public Item findById(Long id, Integer cantidad) {
            Map <String,String> pathVariables = new HashMap<String,String>();
            pathVariables.put("id",id.toString());
            Producto producto = clienteRest.getForObject("http://servicio-productos/{id}",Producto.class,pathVariables);
            return new Item(producto,cantidad);

        }
}
