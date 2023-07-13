package com.skillstorm.project1.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.skillstorm.project1.models.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    
    public Optional<List<Stock>> findAllByQuantityGreaterThanEqual(int minQuantity);

    @Query("update Stock s set s.stock_name = :new_name where id = :stock_id")
    @Modifying
    @Transactional  
    public int updateStockName(@Param("stock_id") int id, @Param("new_name") String newName);
    

}
