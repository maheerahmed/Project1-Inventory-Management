package com.skillstorm.project1.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project1.models.Stock;
import com.skillstorm.project1.services.StockService;

@RestController
@RequestMapping("/stock")
@CrossOrigin("http://127.0.0.1:5500/")
public class StockController {
    
    @Autowired
    StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> findAllStock(){
        List<Stock> stock = stockService.findAllStock();

        return new ResponseEntity<List<Stock>>(stock, HttpStatus.OK);
    }

    @GetMapping("/stock/{id}")
    public ResponseEntity<Stock> findStockById(@PathVariable int id){
        Stock stock = stockService.findStockbyId(id);
        return new ResponseEntity<Stock>(stock, HttpStatus.OK);
    }

    @GetMapping("/quantity/{quantity}")
    public ResponseEntity<List<Stock>> findAllByQuantity(@PathVariable int quantity){
        List<Stock> stock = stockService.findAllByQuantity(quantity);

        if(stock == null){
            return  ResponseEntity.noContent().build();
        }

        return new ResponseEntity<List<Stock>>(stock, HttpStatus.OK);
    }

    @PostMapping("/stock")
    public ResponseEntity<Stock> createStock(@Valid @RequestBody Stock stock){
        Stock newStock = stockService.saveStock(stock);
        return new ResponseEntity<Stock>(newStock, HttpStatus.CREATED);
    }

    @PutMapping("/stock")
    public ResponseEntity<Stock> updateStock(@RequestBody Stock stock){
        Stock newStock = stockService.saveStock(stock);
        return new ResponseEntity<Stock>(newStock, HttpStatus.OK);
    }

    @PutMapping("/stock/updateName") 
    public ResponseEntity<Integer> updateStockName(@RequestBody Stock stock, @RequestParam String newName) {
        
        int updated = stockService.updateStockName(stock, newName);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/stock") 
    public ResponseEntity<Stock> deleteStock(@RequestBody Stock stock) {
        
        stockService.deleteStock(stock);
        return ResponseEntity.noContent().build();
    }
}
