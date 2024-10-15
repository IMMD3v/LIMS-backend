package com.immd3v.limsManager.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "analysis")
public class AnalysisRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //Liquid relationship
    @ManyToOne
    @JoinColumn(name = "liquid_id")
    private Liquid liquid;
    @ManyToOne
    @JoinColumn(name = "container_id")
    private Container container;
    // request status: pending - accepted - cancelled
    private String status;

    //request info
    private String requestedBy;
    private LocalDateTime requestDate;
    private LocalDateTime completionDate;

    //analysis result field
    private Double powerHydrogen;
    private Double turbidity;
    //others

    //CONST
    public AnalysisRequest() {}
    public AnalysisRequest(
            Liquid liquid,
            Container container,
            String status,
            String requestedBy,
            LocalDateTime requestDate,
            LocalDateTime completionDate,
            Double powerHydrogen,
            Double turbidity)
    {
        this.liquid = liquid;
        this.container = container;
        this.status = status;
        this.requestedBy = requestedBy;
        this.requestDate = requestDate;
        this.completionDate = completionDate;
        this.powerHydrogen = powerHydrogen;
        this.turbidity = turbidity;
    }

    //GNS

    public int getId() { return this.id;}

    public Liquid getLiquid() { return this.liquid;}
    public void setLiquid(Liquid liquid) { this.liquid = liquid;}
    public Container getContainer() { return this.container;}
    public void setContainer(Container container) { this.container = container;}
    public String getStatus() { return this.status;}
    public void setStatus(String status) { this.status = status;}
    public String getRequestedBy() { return this.requestedBy;}
    public void setRequestedBy(String requestedBy) { this.requestedBy = requestedBy;}
    public LocalDateTime getRequestDate() { return this.requestDate;}
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate;}
    public LocalDateTime getCompletionDate() { return this.completionDate;}
    public void setCompletionDate(LocalDateTime completionDate) { this.completionDate = completionDate;}
    public Double getPowerHydrogen() { return this.powerHydrogen;}
    public void setPowerHydrogen(Double powerHydrogen) { this.powerHydrogen = powerHydrogen;}
    public Double getTurbidity() { return this.turbidity;}
    public void setTurbidity(Double turbidity) { this.turbidity = turbidity;}
}
