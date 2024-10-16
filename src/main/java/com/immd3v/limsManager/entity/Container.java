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
    private Double usedCapacity;
    @ManyToOne
    @JoinColumn(name = "liquid_id")
    private Liquid liquid;
    private boolean inUse;
    private String material;
    //another fields

    //constructors
    public Container () {}
    public Container (
            String name,
            Double capacity,
            Double usedCapacity,
            Liquid liquid,
            boolean inUse,
            String material
    ) {
        this.name = name;
        this.capacity = capacity;
        this.usedCapacity = usedCapacity;
        this.liquid = liquid;
        this.inUse = inUse;
        this.material = material;
    }

    //GNS
    public int getId() { return this.id;}
    public String getName() { return this.name;}
    public void setName(String name) { this.name = name;}
    public Double getCapacity() { return this.capacity;}
    public void setCapacity(Double capacity) { this.capacity = capacity;}
    public Double getUsedCapacity() { return this.usedCapacity;}
    public void setUsedCapacity(Double usedCapacity) { this.usedCapacity = usedCapacity;}
    public Liquid getLiquid() { return this.liquid;}
    public void setLiquid(Liquid liquid) { this.liquid = liquid;}
    public boolean isInUse() { return this.inUse;}
    public void setInUse(boolean inUse) { this.inUse = inUse;}
    public String getMaterial() { return this.material;}
    public void setMaterial(String material) { this.material = material;}
}
