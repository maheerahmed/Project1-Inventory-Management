package com.skillstorm.project1.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project1.models.Stock;
import com.skillstorm.project1.models.Warehouse;
import com.skillstorm.project1.repositories.StockRepository;

@Service
public class StockService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    WarehouseService warehouseService;

    public List<Stock> findAllStock(){
        return stockRepository.findAll();
    }
    
    public Stock findStockbyId(int id){
        Optional<Stock> stock = stockRepository.findById(id);

        if(stock.isPresent()){
            return stock.get();
        }

        return null;
    }

    public List<Stock> findAllByQuantity(int quantity){
        Optional<List<Stock>> stock = stockRepository.findAllByQuantityGreaterThanEqual(quantity);

        if (stock.isPresent()){
            return stock.get();
        }

        return null;
    }

    public Stock saveStock(Stock stock){
        Warehouse warehouseWithId = warehouseService.saveWarehouse(stock.getWarehouse());
        stock.setWarehouse(warehouseWithId);
        return stockRepository.save(stock);
    }

    public int updateStockName(Stock stock, String newName){
        return stockRepository.updateStockName(stock.getId(), newName);
    }

    public void deleteStock(Stock stock){
        stockRepository.delete(stock);
    }


}
