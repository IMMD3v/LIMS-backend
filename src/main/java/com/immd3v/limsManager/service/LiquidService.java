package com.immd3v.limsManager.service;

import com.immd3v.limsManager.dto.LiquidDTO;
import com.immd3v.limsManager.entity.AnalysisRequest;
import com.immd3v.limsManager.entity.Liquid;
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
        List<Liquid> liquidList = liquidRepository.findAll();
        List<LiquidDTO> response = liquidList.stream()
                .map(liquid -> {
                    LiquidDTO liquidDTO = new LiquidDTO();
                    liquidDTO.setId(liquid.getId());
                    liquidDTO.setDescription(liquid.getDescription());
                    liquidDTO.setOrigin(liquid.getOrigin());
                    liquidDTO.setVolume(liquid.getVolume());
                    liquidDTO.setBatch(liquid.getBatch());
                    return liquidDTO;
                })
                .collect(Collectors.toList());
        return response;
    }

    public String saveLiquid(LiquidDTO liquidDTO) {
        Liquid newLiquid = new Liquid();

        newLiquid.setDescription(liquidDTO.getDescription());
        newLiquid.setOrigin(liquidDTO.getOrigin());
        newLiquid.setVolume(liquidDTO.getVolume());
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
        responseDTO.setVolume(requestedLiquid.getVolume());
        responseDTO.setBatch(requestedLiquid.getBatch());

        return responseDTO;
    }

    public LiquidDTO update(int id, LiquidDTO liquidDTO) {
        Optional<Liquid> liquidDetails = liquidRepository.findById(id);

        Liquid updatingLiquid = liquidDetails.get();

        updatingLiquid.setDescription(liquidDTO.getDescription());
        updatingLiquid.setVolume(liquidDTO.getVolume());
        updatingLiquid.setOrigin(liquidDTO.getOrigin());
        updatingLiquid.setBatch(liquidDTO.getBatch());

        liquidRepository.save(updatingLiquid);

        LiquidDTO responseDTO = new LiquidDTO();

        responseDTO.setId(updatingLiquid.getId());
        responseDTO.setDescription(updatingLiquid.getDescription());
        responseDTO.setOrigin(updatingLiquid.getDescription());
        responseDTO.setVolume(updatingLiquid.getVolume());
        responseDTO.setBatch(updatingLiquid.getBatch());

        return responseDTO;

    }

    public Liquid setLiquidType(String description) {
        Liquid requestedLiquid = liquidRepository.findByDescription(description).orElse(null);
        if (requestedLiquid == null) {
            throw new RuntimeException();
        } else {
            return requestedLiquid;
        }
    }

}
