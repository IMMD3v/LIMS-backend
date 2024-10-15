package com.immd3v.limsManager.service;

import com.immd3v.limsManager.dto.ContainerDTO;
import com.immd3v.limsManager.entity.Container;
import com.immd3v.limsManager.exception.GeneralExceptionResponse;
import com.immd3v.limsManager.message.Message;
import com.immd3v.limsManager.repository.ContainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContainerService {
    @Autowired
    private ContainerRepository containerRepository;
    @Autowired
    private LiquidService liquidService;

    //CRUD methods
    public List<ContainerDTO> getContainers() {
        //try return the list of all
        try {
            List<Container> containersList = containerRepository.findAll();
            if (containersList.isEmpty()) {
                throw new GeneralExceptionResponse("resource not found!");
            }
            return containersList.stream()
                    .map(container -> {
                        ContainerDTO containerDTO = new ContainerDTO();
                        containerDTO.setId(container.getId());
                        containerDTO.setName(container.getName());
                        containerDTO.setCapacity(container.getCapacity());
                        containerDTO.setUsedCapacity(container.getUsedCapacity());
                        // Verificar si el liquid no es null antes de asignar el liquidId
                        if (container.getLiquid() != null) {
                            containerDTO.setLiquidId(container.getLiquid().getId());
                        } else {
                            containerDTO.setLiquidId(0);
                        }
                        containerDTO.setMaterial(container.getMaterial());
                        containerDTO.setInUse(container.isInUse());
                        return containerDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ContainerDTO> getEmptyContainers() {
        //try return the list of all
        try {
            List<Container> emptyContainerList = containerRepository.findByInUseFalse();
            if (emptyContainerList.isEmpty()) {
                throw new GeneralExceptionResponse("resource not found!");
            }
            return emptyContainerList.stream()
                    .map(container -> {
                        ContainerDTO containerDTO = new ContainerDTO();
                        containerDTO.setId(container.getId());
                        containerDTO.setName(container.getName());
                        containerDTO.setCapacity(container.getCapacity());
                        containerDTO.setUsedCapacity(container.getUsedCapacity());
                        // Verificar si el liquid no es null antes de asignar el liquidId
                        if (container.getLiquid() != null) {
                            containerDTO.setLiquidId(container.getLiquid().getId());
                        } else {
                            containerDTO.setLiquidId(0);
                        }
                        containerDTO.setMaterial(container.getMaterial());
                        containerDTO.setInUse(container.isInUse());
                        return containerDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ContainerDTO> getUsedContainers() {
        //try return the list of all
        try {
            List<Container> emptyContainerList = containerRepository.findByInUseTrue();
            if (emptyContainerList.isEmpty()) {
                throw new GeneralExceptionResponse("resource not found!");
            }
            return emptyContainerList.stream()
                    .map(container -> {
                        ContainerDTO containerDTO = new ContainerDTO();
                        containerDTO.setId(container.getId());
                        containerDTO.setName(container.getName());
                        containerDTO.setCapacity(container.getCapacity());
                        containerDTO.setUsedCapacity(container.getUsedCapacity());
                        containerDTO.setLiquidId(container.getLiquid().getId());
                        containerDTO.setMaterial(container.getMaterial());
                        containerDTO.setInUse(container.isInUse());
                        return containerDTO;
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String saveNewContainer(ContainerDTO containerDTO) {
        //create new instance
        Container newContainer = new Container();
        //assign new values
        newContainer.setName(containerDTO.getName());
        newContainer.setCapacity(containerDTO.getCapacity());
        newContainer.setMaterial(containerDTO.getMaterial());
        newContainer.setInUse(false);
        newContainer.setUsedCapacity(0.00);
        //save repository
        containerRepository.save(newContainer);
        //return response
        return "container saved successfully";
    }

    public String deleteContainer(int id) {
        Optional<Container> container = containerRepository.findById(id);
        Container requestedElimination = container.get();
        requestedElimination.setLiquid(null);
        containerRepository.save(requestedElimination);

        containerRepository.deleteById(id);
        return "record deleted successfully!";
    }

    public ContainerDTO updateContainer(int id, ContainerDTO containerDTO) {
        Optional<Container> existingContainer = containerRepository.findById(id);

        Container updatingContainer = existingContainer.get();

        updatingContainer.setCapacity(containerDTO.getCapacity());
        updatingContainer.setUsedCapacity(containerDTO.getUsedCapacity());
        updatingContainer.setName(containerDTO.getName());

        if (containerDTO.getLiquidId() != 0) {
            updatingContainer.setLiquid(liquidService.setLiquidType(containerDTO.getLiquidId()));
        } else {
            updatingContainer.setLiquid(null);
        }
        updatingContainer.setMaterial(containerDTO.getMaterial());
        //is required in assignment cases
        updatingContainer.setInUse(containerDTO.isInUse());

        containerRepository.save(updatingContainer);

        ContainerDTO responseDTO = new ContainerDTO();

        responseDTO.setId(updatingContainer.getId());
        responseDTO.setName(updatingContainer.getName());
        responseDTO.setCapacity(updatingContainer.getCapacity());
        if (updatingContainer.getLiquid() != null) {
            responseDTO.setLiquidId(updatingContainer.getLiquid().getId());
        } else {
            containerDTO.setLiquidId(0);
        }
        responseDTO.setMaterial(updatingContainer.getMaterial());
        return responseDTO;
    }

    public ContainerDTO getOneById(int id) {
        Optional<Container> containerDetails = containerRepository.findById(id);
        System.out.println("id = " + id);


        Container requestedContainer = containerDetails.get();

        ContainerDTO responseDTO = new ContainerDTO();

        responseDTO.setId(requestedContainer.getId());
        responseDTO.setName(requestedContainer.getName());
        responseDTO.setCapacity(requestedContainer.getCapacity());
        responseDTO.setUsedCapacity(requestedContainer.getUsedCapacity());
        // Verificar si el liquid no es null antes de asignar el liquidId
        if (requestedContainer.getLiquid() != null) {
            responseDTO.setLiquidId(requestedContainer.getLiquid().getId());
        } else {
            responseDTO.setLiquidId(0);
        }
        responseDTO.setMaterial(requestedContainer.getMaterial());
        responseDTO.setInUse(requestedContainer.isInUse());

        return responseDTO;
    }

    public Container setContainerType(Integer containerId) {
        Container requestedContainer = containerRepository.findById(containerId).orElse(null);
        if (requestedContainer == null) {
            throw new RuntimeException();
        } else {
            return requestedContainer;
        }
    }
}
