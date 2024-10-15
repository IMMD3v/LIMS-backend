package com.immd3v.limsManager.dto;

public class ContainerDTO {
    private int id;
    private String name;
    private Double capacity;
    private Double usedCapacity;
    private int liquidId;
    private String material;
    private boolean inUse;

    public ContainerDTO() {}
    public ContainerDTO(
            String name,
            Double capacity,
            Double usedCapacity,
            int liquidId,
            String material,
            boolean inUse) {
        this.name = name;
        this.capacity = capacity;
        this.usedCapacity = usedCapacity;
        this.liquidId = liquidId;
        this.material = material;
        this.inUse = inUse;
    }

    public int getId() { return this.id;}
    public void setId(int id) { this.id = id;}
    public String getName() { return this.name;}
    public void setName(String name) { this.name = name;}
    public Double getCapacity() { return this.capacity;}
    public void setCapacity(Double capacity) { this.capacity = capacity;}
    public void setUsedCapacity(Double usedCapacity) { this.usedCapacity = usedCapacity;}
    public Double getUsedCapacity() { return this.usedCapacity;}
    public int getLiquidId() { return this.liquidId;}
    public void setLiquidId(int liquidId) { this.liquidId = liquidId;}
    public boolean isInUse() { return this.inUse;}
    public void setInUse(boolean inUse) { this.inUse = inUse;}
    public String getMaterial() { return this.material;}
    public void setMaterial(String material) { this.material = material;}
}
