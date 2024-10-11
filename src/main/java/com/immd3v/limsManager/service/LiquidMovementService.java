package com.immd3v.limsManager.service;

import com.immd3v.limsManager.dto.LiquidDTO;
import com.immd3v.limsManager.dto.LiquidIdDTO;
import com.immd3v.limsManager.entity.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LiquidMovementService {
    @Autowired
    private LiquidService liquidService;
    @Autowired
    private ContainerService containerService;
    //in memory liquid to request
    private LiquidDTO liquid;
    //in memory list of empty containers = user selections
    private List<Container> selectedContainers = new ArrayList<>();

    public void captureLiquid(LiquidIdDTO liquidIdDTO) {
        int id = liquidIdDTO.getId();
        LiquidDTO foundLiquid = liquidService.getOneById(id);
        if (foundLiquid == null) {
            throw new RuntimeException("Liquid not found with id " + id);
        }

        if (liquid == null || liquid.getId() != id) {
            liquid = foundLiquid;
        }
        System.out.println("liquid in Memory = " + liquid.getDescription());
    }
}
