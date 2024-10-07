package com.immd3v.limsManager.dto;

public class LiquidDTO {
    private int id;
    private String description;
    private String origin;
    private Double volume;
    private String batch;

    public LiquidDTO() {}
    public LiquidDTO(String description, String origin, Double volume, String batch) {
        this.description = description;
        this.origin = origin;
        this.volume = volume;
        this.batch = batch;
    }

    public int getId() { return this.id;}
    public void setId(int id) { this.id = id;}
    public String getDescription() { return this.description;}
    public void setDescription(String description) { this.description = description;}
    public String getOrigin() { return this.origin;}
    public void setOrigin(String origin) { this.origin = origin;}
    public Double getVolume() { return this.volume;}
    public void setVolume(Double volume) { this.volume = volume;}
    public String getBatch() { return this.batch;}
    public void setBatch(String batch) { this.batch = batch;}
}
