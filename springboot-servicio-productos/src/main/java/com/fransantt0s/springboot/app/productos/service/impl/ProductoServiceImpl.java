package com.fransantt0s.springboot.app.productos.service.impl;

import com.fransantt0s.springboot.app.productos.dao.ProductoDAO;
import com.fransantt0s.springboot.app.productos.entity.Producto;
import com.fransantt0s.springboot.app.productos.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoDAO productoDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id)  {
        return productoDAO.findById(id).orElse(null);
    }

    @Override
    public void deleteByid(Long id){
        if(id == null){
            throw new RuntimeException("El producto que quiere eliminar no existe");
        }
        else{
            productoDAO.deleteById(id);
        }

    }

    public Producto saveProduct(Producto producto){
        productoDAO.save(producto);
        return producto;
    }

}
