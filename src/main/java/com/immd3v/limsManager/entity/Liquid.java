package com.immd3v.limsManager.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "liquids")
public class Liquid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private String origin;
    private Double actualVolume;
    private Double originalVolume;
    private String batch;

    public Liquid() {}
    public Liquid(
            String description,
            String origin,
            Double originalVolume,
            String batch) {
        this.description = description;
        this.origin = origin;
        this.originalVolume = originalVolume;
        this.actualVolume = originalVolume;
        this.batch = batch;
    }

    public int getId() { return this.id;}
    public String getDescription() { return this.description;}
    public void setDescription(String description) { this.description = description;}
    public String getOrigin() { return this.origin;}
    public void setOrigin(String origin) { this.origin = origin;}
    public Double getActualVolume() { return this.actualVolume;}
    public void setActualVolume(Double actualVolume) { this.actualVolume = actualVolume;}
    public Double getOriginalVolume() { return this.originalVolume;}
    public void setOriginalVolume(Double originalVolume) { this.originalVolume = originalVolume;}
    public String getBatch() { return this.batch;}
    public void setBatch(String batch) { this.batch = batch;}
}
