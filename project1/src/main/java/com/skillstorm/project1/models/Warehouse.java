package com.skillstorm.project1.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "WAREHOUSE")
public class Warehouse {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String warehouse_name;

    @JsonBackReference
    @OneToMany(targetEntity = Stock.class, mappedBy = "warehouse")
    private Set<Stock> stock;

    public Warehouse() {
    }
    
    public Warehouse(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public Warehouse(String warehouse_name, Set<Stock> stock) {
        this.warehouse_name = warehouse_name;
        this.stock = stock;
    }

    public Warehouse(int id, String warehouse_name, Set<Stock> stock) {
        this.id = id;
        this.warehouse_name = warehouse_name;
        this.stock = stock;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public Set<Stock> getStock() {
        return stock;
    }

    public void setStock(Set<Stock> stock) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((warehouse_name == null) ? 0 : warehouse_name.hashCode());
        result = prime * result + ((stock == null) ? 0 : stock.hashCode());
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
        Warehouse other = (Warehouse) obj;
        if (id != other.id)
            return false;
        if (warehouse_name == null) {
            if (other.warehouse_name != null)
                return false;
        } else if (!warehouse_name.equals(other.warehouse_name))
            return false;
        if (stock == null) {
            if (other.stock != null)
                return false;
        } else if (!stock.equals(other.stock))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", warehouse_name=" + warehouse_name +  "]";
    }
    
}
