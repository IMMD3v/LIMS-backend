package com.immd3v.limsManager.dto;

import java.time.LocalDateTime;

public class AnalysisRequestDTO {
    private int id;
    private int liquidId;
    private int containerId;
    private String status;
    private String requestedBy;
    private LocalDateTime requestDate;
    private LocalDateTime completionDate;
    //Analysis Protocol
    //jackson no sabe como manejar palabras tan cortas como pH por lo que
    //su camelCase la define como ph en respuestas. Entonces cambio en el frontend, mas fácil
    private Double powerHydrogen;
    private Double turbidity;

    public AnalysisRequestDTO() {}
    public AnalysisRequestDTO(
            int liquid,
            int container,
            String status,
            String requestedBy,
            LocalDateTime requestDate,
            LocalDateTime completionDate,
            Double powerHydrogen,
            Double turbidity)
    {
        this.liquidId = liquid;
        this.containerId = container;
        this.status = status;
        this.requestedBy = requestedBy;
        this.requestDate = requestDate;
        this.completionDate = completionDate;
        this.powerHydrogen = powerHydrogen;
        this.turbidity = turbidity;
    }

    //GNS

    public int getId() { return this.id;}
    public void setId(int id) { this.id = id;}
    public int getContainerId() { return this.containerId;}
    public void setContainerId(int containerId) { this.containerId = containerId;}
    public int getLiquidId() { return this.liquidId;}
    public void setLiquidId(int liquidId) { this.liquidId = liquidId;}
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
