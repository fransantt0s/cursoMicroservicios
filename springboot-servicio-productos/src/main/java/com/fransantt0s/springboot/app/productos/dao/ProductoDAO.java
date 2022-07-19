package com.fransantt0s.springboot.app.productos.dao;

import com.fransantt0s.springboot.app.productos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDAO extends JpaRepository<Producto,Long> {
}
