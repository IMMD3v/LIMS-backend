package com.immd3v.limsManager.service;

import com.immd3v.limsManager.dto.AnalysisRequestDTO;
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

    public List<AnalysisRequestDTO> getAllAnalysis() {
        List<AnalysisRequest> analysis = analysisRequestRepository.findAll();

        List<AnalysisRequestDTO> response = analysis.stream()
                .map(analysisRequest -> {

                    AnalysisRequestDTO analysisRequestDTO = new AnalysisRequestDTO();

                    analysisRequestDTO.setId(analysisRequest.getId());
                    analysisRequestDTO.setLiquid(analysisRequest.getLiquid().getDescription());
                    analysisRequestDTO.setStatus(analysisRequest.getStatus());
                    analysisRequestDTO.setRequestedBy(analysisRequest.getRequestedBy());
                    analysisRequestDTO.setRequestDate(analysisRequest.getRequestDate());
                    analysisRequestDTO.setCompletionDate(analysisRequest.getCompletionDate());
                    analysisRequestDTO.setPH(analysisRequest.getPH());
                    analysisRequestDTO.setTurbidity(analysisRequest.getTurbidity());
                    return analysisRequestDTO;
                })
                .collect(Collectors.toList());
                return response;
    }

    public String createOne(AnalysisRequestDTO analysisRequestDTO) {
        AnalysisRequest analysisRequest = new AnalysisRequest();
        //request fields
        analysisRequest.setLiquid(liquidService.setLiquidType(analysisRequestDTO.getLiquid()));
        analysisRequest.setRequestedBy(analysisRequestDTO.getRequestedBy());
        //automatic fields
        analysisRequest.setStatus("created");
        analysisRequest.setRequestDate(LocalDateTime.now());
        analysisRequest.setPH(null);
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
        response.setLiquid(requestedAnalysis.getLiquid().getDescription());
        response.setStatus(requestedAnalysis.getStatus());
        response.setRequestedBy(requestedAnalysis.getRequestedBy());
        response.setRequestDate(requestedAnalysis.getRequestDate());
        response.setCompletionDate(requestedAnalysis.getCompletionDate());
        response.setPH(requestedAnalysis.getPH());
        response.setTurbidity(requestedAnalysis.getTurbidity());

        return response;
    }

    public AnalysisRequestDTO update(Integer id, AnalysisRequestDTO requestDTO) {
        Optional<AnalysisRequest> existingAnalysis = analysisRequestRepository.findById(id);

        AnalysisRequest updatingAnalysis = existingAnalysis.get();

        updatingAnalysis.setStatus(requestDTO.getStatus());
        updatingAnalysis.setLiquid(liquidService.setLiquidType(requestDTO.getLiquid()));
        updatingAnalysis.setRequestedBy(requestDTO.getRequestedBy());
        updatingAnalysis.setRequestDate(requestDTO.getRequestDate());
        updatingAnalysis.setCompletionDate(requestDTO.getCompletionDate());
        updatingAnalysis.setPH(requestDTO.getPH());
        updatingAnalysis.setTurbidity(requestDTO.getTurbidity());

        analysisRequestRepository.save(updatingAnalysis);

        AnalysisRequestDTO responseDTO = new AnalysisRequestDTO();
        responseDTO.setStatus(updatingAnalysis.getStatus());
        responseDTO.setLiquid(updatingAnalysis.getLiquid().getDescription());
        responseDTO.setRequestedBy(updatingAnalysis.getRequestedBy());
        responseDTO.setRequestDate(updatingAnalysis.getRequestDate());
        responseDTO.setCompletionDate(updatingAnalysis.getCompletionDate());
        responseDTO.setPH(updatingAnalysis.getPH());
        responseDTO.setTurbidity(updatingAnalysis.getTurbidity());
        return responseDTO;
    }
}
