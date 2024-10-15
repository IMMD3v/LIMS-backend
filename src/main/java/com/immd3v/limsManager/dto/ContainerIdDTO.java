package com.immd3v.limsManager.dto;

public class ContainerIdDTO {
    private int containerId;
    private int liquidId;

    public ContainerIdDTO() {}
    public ContainerIdDTO(
            int containerId,
            int liquidId) {
        this.containerId = containerId;
        this.liquidId = liquidId;
    }

    public int getContainerId() { return this.containerId;}
    public void setContainerId(int containerId) { this.containerId = containerId;}
    public int getLiquidId() { return this.liquidId;}
    public void setLiquidId(int liquidId) { this.liquidId = liquidId;}
}
