package com.immd3v.limsManager.dto;

import java.time.LocalDateTime;

public class AnalysisRequestDTO {
    private int id;
    private String liquid;
    private String status;
    private String requestedBy;
    private LocalDateTime requestDate;
    private LocalDateTime completionDate;
    //Analysis Protocol
    //jackson no sabe como manejar palabras tan cortas como pH por lo que
    //su camelCase la define como ph en respuestas. Entonces cambio en el frontend, mas fácil
    private Double pH;
    private Double turbidity;

    public AnalysisRequestDTO() {}
    public AnalysisRequestDTO(
            String liquid,
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
    public void setId(int id) { this.id = id;}
    public String getLiquid() { return this.liquid;}
    public void setLiquid(String liquid) { this.liquid = liquid;}
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
