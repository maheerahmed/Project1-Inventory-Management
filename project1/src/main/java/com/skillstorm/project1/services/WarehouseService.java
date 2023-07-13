package com.skillstorm.project1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.repositories.WarehouseRepository;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository repository;

    public List<Warehouse> findAllWarehouses() {
        return repository.findAll();
    }

    public Warehouse saveWarehouse(Warehouse warehouse){
        return repository.save(warehouse);
    }
    
}
