package com.fransantt0s.springboot.app.productos.service;

import com.fransantt0s.springboot.app.productos.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProductoService {

    public List<Producto> findAll();
    public Producto findById(Long id);
    public void deleteByid(Long id);
    public Producto saveProduct(Producto producto);


}
