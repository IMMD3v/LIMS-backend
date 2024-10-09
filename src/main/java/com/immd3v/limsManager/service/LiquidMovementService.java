package com.immd3v.limsManager.service;

import com.immd3v.limsManager.dto.ContainerDTO;
import com.immd3v.limsManager.dto.LiquidDTO;
import com.immd3v.limsManager.entity.Liquid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LiquidMovementService {
    @Autowired
    private LiquidService liquidService;
    @Autowired
    private ContainerService containerService;

    public String assignLiquidToContainer(int liquidId, int containerId, double volume) {
        // Buscar el líquido
        LiquidDTO liquidDetails = liquidService.getOneById(liquidId);
        //y el contenedor
        ContainerDTO containerDetails = containerService.getOneById(containerId);
        return null;
    }
}
