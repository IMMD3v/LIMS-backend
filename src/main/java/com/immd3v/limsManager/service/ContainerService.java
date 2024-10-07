package com.immd3v.limsManager.service;

import com.immd3v.limsManager.dto.ContainerDTO;
import com.immd3v.limsManager.entity.Container;
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

    //CRUD methods
    public List<ContainerDTO> getContainers() {
        //return the list of all
        List<Container> containersList = containerRepository.findAll();
        List<ContainerDTO> response = containersList.stream()
                .map(container -> {
                    ContainerDTO containerDTO = new ContainerDTO();
                    containerDTO.setId(container.getId());
                    containerDTO.setName(container.getName());
                    containerDTO.setCapacity(container.getCapacity());
                    containerDTO.setLiquidType(container.getLiquidType());
                    return containerDTO;
                })
                .collect(Collectors.toList());
        return response;
    }

    public String saveNewContainer(ContainerDTO containerDTO) {
        //create new instance
        Container newContainer = new Container();
        //assign new values
        newContainer.setName(containerDTO.getName());
        newContainer.setCapacity(containerDTO.getCapacity());
        //save repository
        containerRepository.save(newContainer);
        //return response
        return "container saved successfully";
    }

    public String deleteContainer(int id) {
        containerRepository.deleteById(id);
        return "record deleted successfully!";
    }

    public ContainerDTO updateContainer(int id, ContainerDTO containerDTO) {
        Optional<Container> existingContainer = containerRepository.findById(id);

        Container updatingContainer = existingContainer.get();

        updatingContainer.setCapacity(containerDTO.getCapacity());
        updatingContainer.setName(containerDTO.getName());
        updatingContainer.setLiquidType(containerDTO.getLiquidType());

        containerRepository.save(updatingContainer);

        ContainerDTO responseDTO = new ContainerDTO();

        responseDTO.setId(updatingContainer.getId());
        responseDTO.setName(updatingContainer.getName());
        responseDTO.setCapacity(updatingContainer.getCapacity());
        responseDTO.setLiquidType(updatingContainer.getLiquidType());
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
        responseDTO.setLiquidType(requestedContainer.getLiquidType());

        return responseDTO;
    }
}
