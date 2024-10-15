package com.immd3v.limsManager.service;

import com.immd3v.limsManager.dto.AnalysisRequestDTO;
import com.immd3v.limsManager.dto.ContainerDTO;
import com.immd3v.limsManager.entity.AnalysisRequest;
import com.immd3v.limsManager.repository.AnalysisRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnalysisRequestService {
    @Autowired
    private AnalysisRequestRepository analysisRequestRepository;
    @Autowired
    private LiquidService liquidService;
    @Autowired
    private ContainerService containerService;

    public List<AnalysisRequestDTO> getAllAnalysis() {
        List<AnalysisRequest> analysis = analysisRequestRepository.findAll();

        List<AnalysisRequestDTO> response = analysis.stream()
                .map(analysisRequest -> {

                    AnalysisRequestDTO analysisRequestDTO = new AnalysisRequestDTO();

                    analysisRequestDTO.setId(analysisRequest.getId());
                    analysisRequestDTO.setLiquidId(analysisRequest.getLiquid().getId());
                    analysisRequestDTO.setContainerId(analysisRequest.getContainer().getId());
                    analysisRequestDTO.setStatus(analysisRequest.getStatus());
                    analysisRequestDTO.setRequestedBy(analysisRequest.getRequestedBy());
                    analysisRequestDTO.setRequestDate(analysisRequest.getRequestDate());
                    analysisRequestDTO.setCompletionDate(analysisRequest.getCompletionDate());
                    analysisRequestDTO.setPowerHydrogen(analysisRequest.getPowerHydrogen());
                    analysisRequestDTO.setTurbidity(analysisRequest.getTurbidity());

                    return analysisRequestDTO;
                })
                .collect(Collectors.toList());
                return response;
    }

    public String createOne(AnalysisRequestDTO analysisRequestDTO) {
        // Obtener el container por el id
        ContainerDTO container = containerService.getOneById(analysisRequestDTO.getContainerId());

        // Validar que el container tiene un líquido asignado y que está en uso
        if (container.getUsedCapacity() <= 0) {
            return "Error: El container seleccionado está vacío.";
        }
        AnalysisRequest analysisRequest = new AnalysisRequest();
        //request fields
        analysisRequest.setLiquid(liquidService.setLiquidType(analysisRequestDTO.getLiquidId()));
        analysisRequest.setContainer(containerService.setContainerType(analysisRequestDTO.getContainerId()));
        analysisRequest.setRequestedBy(analysisRequestDTO.getRequestedBy());
        //automatic fields
        analysisRequest.setStatus("created");
        analysisRequest.setRequestDate(LocalDateTime.now());
        analysisRequest.setPowerHydrogen(null);
        analysisRequest.setTurbidity(null);

        analysisRequestRepository.save(analysisRequest);

        return "record successfully created!";
    }

    public String deleteRequest(int id) {
        analysisRequestRepository.deleteById(id);
        return "request deleted successfully";
    }

    public AnalysisRequestDTO getOneById(int id) {
        Optional<AnalysisRequest> requestDetails = analysisRequestRepository.findById(id);

        AnalysisRequest requestedAnalysis = requestDetails.get();

        AnalysisRequestDTO response = new AnalysisRequestDTO();

        response.setId(requestedAnalysis.getId());
        response.setLiquidId(requestedAnalysis.getLiquid().getId());
        response.setContainerId(requestedAnalysis.getContainer().getId());
        response.setStatus(requestedAnalysis.getStatus());
        response.setRequestedBy(requestedAnalysis.getRequestedBy());
        response.setRequestDate(requestedAnalysis.getRequestDate());
        response.setCompletionDate(requestedAnalysis.getCompletionDate());
        response.setPowerHydrogen(requestedAnalysis.getPowerHydrogen());
        response.setTurbidity(requestedAnalysis.getTurbidity());

        return response;
    }

    public AnalysisRequestDTO update(Integer id, AnalysisRequestDTO requestDTO) {
        Optional<AnalysisRequest> existingAnalysis = analysisRequestRepository.findById(id);

        AnalysisRequest updatingAnalysis = existingAnalysis.get();

        updatingAnalysis.setStatus(requestDTO.getStatus());
        updatingAnalysis.setLiquid(liquidService.setLiquidType(requestDTO.getLiquidId()));
        updatingAnalysis.setContainer(containerService.setContainerType(requestDTO.getContainerId()));
        updatingAnalysis.setRequestedBy(requestDTO.getRequestedBy());
        updatingAnalysis.setRequestDate(requestDTO.getRequestDate());
        updatingAnalysis.setCompletionDate(requestDTO.getCompletionDate());
        updatingAnalysis.setPowerHydrogen(requestDTO.getPowerHydrogen());
        updatingAnalysis.setTurbidity(requestDTO.getTurbidity());

        analysisRequestRepository.save(updatingAnalysis);

        AnalysisRequestDTO responseDTO = new AnalysisRequestDTO();
        responseDTO.setStatus(updatingAnalysis.getStatus());
        responseDTO.setLiquidId(updatingAnalysis.getLiquid().getId());
        responseDTO.setContainerId(updatingAnalysis.getContainer().getId());
        responseDTO.setRequestedBy(updatingAnalysis.getRequestedBy());
        responseDTO.setRequestDate(updatingAnalysis.getRequestDate());
        responseDTO.setCompletionDate(updatingAnalysis.getCompletionDate());
        responseDTO.setPowerHydrogen(updatingAnalysis.getPowerHydrogen());
        responseDTO.setTurbidity(updatingAnalysis.getTurbidity());
        return responseDTO;
    }
}
