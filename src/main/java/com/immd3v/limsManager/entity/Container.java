package com.immd3v.limsManager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "containers")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Double capacity;
    private String liquidType;
    //another fields

    //constructors
    public Container () {}
    public Container (String name, Double capacity, String liquidType) {
        this.name = name;
        this.capacity = capacity;
        this.liquidType = liquidType;
    }

    //GNS
    public int getId() { return this.id;}
    public String getName() { return this.name;}
    public void setName(String name) { this.name = name;}
    public Double getCapacity() { return this.capacity;}
    public void setCapacity(Double capacity) { this.capacity = capacity;}
    public String getLiquidType() { return this.liquidType;}
    public void setLiquidType(String liquidType) { this.liquidType = liquidType;}
}
