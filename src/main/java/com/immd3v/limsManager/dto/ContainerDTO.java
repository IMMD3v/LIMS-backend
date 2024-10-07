package com.immd3v.limsManager.dto;

public class ContainerDTO {
    private int id;
    private String name;
    private Double capacity;
    private String liquidType;

    public ContainerDTO() {}
    public ContainerDTO(String name, Double capacity, String liquidType) {
        this.name = name;
        this.capacity = capacity;
        this.liquidType = liquidType;
    }

    public int getId() { return this.id;}
    public void setId(int id) { this.id = id;}
    public String getName() { return this.name;}
    public void setName(String name) { this.name = name;}
    public Double getCapacity() { return this.capacity;}
    public void setCapacity(Double capacity) { this.capacity = capacity;}
    public String getLiquidType() { return this.liquidType;}
    public void setLiquidType(String liquidType) { this.liquidType = liquidType;}
}
