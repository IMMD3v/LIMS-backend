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
    // request status: pending - accepted - cancelled
    private String status;

    //request info
    private String requestedBy;
    private LocalDateTime requestDate;
    private LocalDateTime completionDate;

    //analysis result field
    private Double pH;
    private Double turbidity;
    //others

    //CONST
    public AnalysisRequest() {}
    public AnalysisRequest(
            Liquid liquid,
            String status,
            String requestedBy,
            LocalDateTime requestDate,
            LocalDateTime completionDate,
            Double pH,
            Double turbidity)
    {
        this.liquid = liquid;
        this.status = status;
        this.requestedBy = requestedBy;
        this.requestDate = requestDate;
        this.completionDate = completionDate;
        this.pH = pH;
        this.turbidity = turbidity;
    }

    //GNS

    public int getId() { return this.id;}

    public Liquid getLiquid() { return this.liquid;}
    public void setLiquid(Liquid liquid) { this.liquid = liquid;}
    public String getStatus() { return this.status;}
    public void setStatus(String status) { this.status = status;}
    public String getRequestedBy() { return this.requestedBy;}
    public void setRequestedBy(String requestedBy) { this.requestedBy = requestedBy;}
    public LocalDateTime getRequestDate() { return this.requestDate;}
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate;}
    public LocalDateTime getCompletionDate() { return this.completionDate;}
    public void setCompletionDate(LocalDateTime completionDate) { this.completionDate = completionDate;}
    public Double getPH() { return this.pH;}
    public void setPH(Double pH) { this.pH = pH;}
    public Double getTurbidity() { return this.turbidity;}
    public void setTurbidity(Double turbidity) { this.turbidity = turbidity;}
}
