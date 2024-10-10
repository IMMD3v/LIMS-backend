package com.immd3v.limsManager.controller;

import com.immd3v.limsManager.dto.ContainerDTO;
import com.immd3v.limsManager.entity.Container;
import com.immd3v.limsManager.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/containers")
@CrossOrigin(origins ="http://localhost:4200")
public class ContainerController {
    @Autowired
    private ContainerService containerService;

    @GetMapping("/listAll")
    public List<ContainerDTO> findAll() {
        return containerService.getContainers();
    }
    @GetMapping("/listAllEmpty")
    public List<ContainerDTO> findAllEmpty() {
        return containerService.getEmptyContainers();
    }

    @PostMapping("/create")
    public String createOne(@RequestBody ContainerDTO container) {
        return containerService.saveNewContainer(container);
    }

    @PutMapping("/update/{id}")
    public ContainerDTO update(@PathVariable Integer id, @RequestBody ContainerDTO requestDTO) {
        return containerService.updateContainer(id, requestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return containerService.deleteContainer(id);
    }

    @GetMapping("/details/{id}")
    public ContainerDTO details(@PathVariable Integer id) {
        return containerService.getOneById(id);
    }
}
