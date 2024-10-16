package com.immd3v.limsManager.service;

import com.immd3v.limsManager.dto.LiquidDTO;
import com.immd3v.limsManager.entity.AnalysisRequest;
import com.immd3v.limsManager.entity.Liquid;
import com.immd3v.limsManager.exception.GeneralExceptionResponse;
import com.immd3v.limsManager.repository.AnalysisRequestRepository;
import com.immd3v.limsManager.repository.LiquidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LiquidService {
    @Autowired
    private LiquidRepository liquidRepository;
    @Autowired
    private AnalysisRequestRepository analysisRequestRepository;

    public List<LiquidDTO> listAll() {
        try {
            List<Liquid> liquidList = liquidRepository.findAll();
            if (liquidList.isEmpty()) {
                throw new GeneralExceptionResponse("resource not found!");
            }
            return liquidList.stream()
                    .map(liquid -> {
                        LiquidDTO liquidDTO = new LiquidDTO();
                        liquidDTO.setId(liquid.getId());
                        liquidDTO.setDescription(liquid.getDescription());
                        liquidDTO.setOrigin(liquid.getOrigin());
                        liquidDTO.setOriginalVolume(liquid.getOriginalVolume());
                        liquidDTO.setActualVolume(liquid.getActualVolume());
                        liquidDTO.setBatch(liquid.getBatch());
                        return liquidDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String saveLiquid(LiquidDTO liquidDTO) {
        Liquid newLiquid = new Liquid();

        newLiquid.setDescription(liquidDTO.getDescription());
        newLiquid.setOrigin(liquidDTO.getOrigin());
        newLiquid.setOriginalVolume(liquidDTO.getOriginalVolume());
        newLiquid.setActualVolume(liquidDTO.getOriginalVolume());
        newLiquid.setBatch(liquidDTO.getBatch());

        liquidRepository.save(newLiquid);

        return "record successfully saved!";
    }

    public String deleteLiquid(int id) {
        Optional<Liquid> liquid = liquidRepository.findById(id);

        Liquid requestedReference = liquid.get();

        List<AnalysisRequest> requests = analysisRequestRepository.findAllByLiquid(requestedReference);

        requests.forEach( request -> {
            request.setLiquid(null);
            analysisRequestRepository.save(request);
        });

        liquidRepository.deleteById(id);

        return "record successfully deleted!";
    }

    public LiquidDTO getOneById(int id) {
        Optional<Liquid> liquidDetails = liquidRepository.findById(id);

        Liquid requestedLiquid = liquidDetails.get();

        LiquidDTO responseDTO = new LiquidDTO();

        responseDTO.setId(requestedLiquid.getId());
        responseDTO.setDescription(requestedLiquid.getDescription());
        responseDTO.setOrigin(requestedLiquid.getOrigin());
        responseDTO.setOriginalVolume(requestedLiquid.getOriginalVolume());
        responseDTO.setActualVolume(requestedLiquid.getActualVolume());
        responseDTO.setBatch(requestedLiquid.getBatch());

        return responseDTO;
    }

    public LiquidDTO update(int id, LiquidDTO liquidDTO) {
        Optional<Liquid> liquidDetails = liquidRepository.findById(id);

        Liquid updatingLiquid = liquidDetails.get();

        updatingLiquid.setDescription(liquidDTO.getDescription());
        updatingLiquid.setOriginalVolume(liquidDTO.getOriginalVolume());
        updatingLiquid.setActualVolume(liquidDTO.getActualVolume());
        updatingLiquid.setOrigin(liquidDTO.getOrigin());
        updatingLiquid.setBatch(liquidDTO.getBatch());

        liquidRepository.save(updatingLiquid);

        LiquidDTO responseDTO = new LiquidDTO();

        responseDTO.setId(updatingLiquid.getId());
        responseDTO.setDescription(updatingLiquid.getDescription());
        responseDTO.setOrigin(updatingLiquid.getDescription());
        responseDTO.setOriginalVolume(updatingLiquid.getOriginalVolume());
        responseDTO.setActualVolume(updatingLiquid.getActualVolume());
        responseDTO.setBatch(updatingLiquid.getBatch());

        return responseDTO;

    }

    public Liquid setLiquidType(Integer liquidId) {
            Liquid requestedLiquid = liquidRepository.findById(liquidId).orElse(null);
            if (requestedLiquid == null) {
                throw new RuntimeException();
            }
            return requestedLiquid;
    }

}
