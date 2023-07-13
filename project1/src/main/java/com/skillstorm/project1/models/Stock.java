package com.skillstorm.project1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "STOCK")
public class Stock {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stock_name")
    private String stock_name;

    @Column
    @Min(1)
    @Max(100)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Stock() {
    }

    public Stock(String stock_name, @Min(1) @Max(100) int quantity, Warehouse warehouse) {
        this.stock_name = stock_name;
        this.quantity = quantity;
        this.warehouse = warehouse;
    }

    public Stock(int id, String stock_name, @Min(1) @Max(100) int quantity, Warehouse warehouse) {
        this.id = id;
        this.stock_name = stock_name;
        this.quantity = quantity;
        this.warehouse = warehouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((stock_name == null) ? 0 : stock_name.hashCode());
        result = prime * result + quantity;
        result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stock other = (Stock) obj;
        if (id != other.id)
            return false;
        if (stock_name == null) {
            if (other.stock_name != null)
                return false;
        } else if (!stock_name.equals(other.stock_name))
            return false;
        if (quantity != other.quantity)
            return false;
        if (warehouse == null) {
            if (other.warehouse != null)
                return false;
        } else if (!warehouse.equals(other.warehouse))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Stock [id=" + id + ", stock_name=" + stock_name + ", quantity=" + quantity + ", warehouse=" + warehouse
                + "]";
    }
}
