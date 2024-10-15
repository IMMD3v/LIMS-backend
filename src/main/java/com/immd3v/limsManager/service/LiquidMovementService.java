package com.immd3v.limsManager.service;

import com.immd3v.limsManager.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LiquidMovementService {
    @Autowired
    private LiquidService liquidService;
    @Autowired
    private ContainerService containerService;
    //in memory liquid to request
    private LiquidDTO liquid;

    public RemainVolumeDTO captureLiquid(LiquidIdDTO liquidIdDTO) {
        int id = liquidIdDTO.getId();
        LiquidDTO foundLiquid = liquidService.getOneById(id);
        if (foundLiquid == null) {
            throw new RuntimeException("Liquid not found with id " + id);
        }

        if (liquid == null || liquid.getId() != id) {
            liquid = foundLiquid;
        }

        //response:
        RemainVolumeDTO response = new RemainVolumeDTO();
        response.setVolume(liquid.getActualVolume());

        return response;

    }
    public RemainVolumeDTO setLiquidToContainer(ContainerIdDTO containerIdDTO) {
        ContainerDTO container = containerService.getOneById(containerIdDTO.getContainerId());
        if (container == null) {
            throw new RuntimeException("Container requested not found");
        }
        if (container.isInUse()) {
            throw new IllegalStateException("Container is already in use.");
        }
        // Verificamos el volumen del líquido en memoria
        double liquidVolume = liquid.getActualVolume();

        // Asignamos el tipo de líquido al contenedor
        container.setLiquidId(containerIdDTO.getLiquidId());

        // Si el volumen del líquido es mayor que la capacidad del contenedor
        if (liquidVolume >= container.getCapacity()) {
            // Asignar la capacidad total del contenedor
            container.setUsedCapacity(container.getCapacity());
            // Descontar la capacidad del líquido en memoria
            liquid.setActualVolume(liquidVolume - container.getCapacity());
        } else {
            // Si el volumen del líquido es menor que la capacidad del contenedor
            container.setUsedCapacity(liquidVolume);
            // Descontar el volumen del líquido en memoria
            liquid.setActualVolume(0.00); // El líquido se agota en memoria
        }
        container.setInUse(true);
        //update state of container
        containerService.updateContainer(containerIdDTO.getContainerId(), container);
        //update state of liquid
        liquidService.update(liquid.getId(), liquid);

        //response:
        RemainVolumeDTO response = new RemainVolumeDTO();
        response.setVolume(liquid.getActualVolume());

        return response;
    }
}
