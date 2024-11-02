package com.example.campodetiro.model;

import com.example.campodetiro.ui.home.recycler.transformer.RecyclerItem;
import com.example.campodetiro.ui.home.recycler.transformer.SubItem;

@RecyclerItem
public class Producto {
    @SubItem(primary = true)
    private String name;
    private String description;
    @SubItem(name="precio")
    private Double price;
    @SubItem(ignore = true)
    private int vendorId;

    public Producto(){}

    public Producto(String name, String description, Double price, int vendorId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
