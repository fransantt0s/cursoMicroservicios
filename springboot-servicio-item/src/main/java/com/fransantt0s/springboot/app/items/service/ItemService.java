package com.fransantt0s.springboot.app.items.service;

import com.fransantt0s.springboot.app.items.entity.Item;

import java.util.List;

public interface ItemService {
    public List <Item> findAll();
    public Item findById(Long id , Integer cantidad);
}
