package com.immd3v.limsManager.controller;

import com.immd3v.limsManager.dto.ContainerDTO;
import com.immd3v.limsManager.entity.Container;
import com.immd3v.limsManager.message.Message;
import com.immd3v.limsManager.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/containers")
@CrossOrigin(origins ="http://localhost:4200")
public class ContainerController {
    @Autowired
    private ContainerService containerService;

    @GetMapping("/listAll")
    public ResponseEntity<List<ContainerDTO>> findAll() {
        List<ContainerDTO> response = containerService.getContainers();
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @GetMapping("/listAllEmpty")
    public ResponseEntity<List<ContainerDTO>> findAllEmpty() {
        List<ContainerDTO> response = containerService.getEmptyContainers();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/listAllInUse")
    public ResponseEntity<List<ContainerDTO>> findAllInUse() {
        List<ContainerDTO>response = containerService.getUsedContainers();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOne(@RequestBody ContainerDTO container) {
        String response = containerService.saveNewContainer(container);
        return new ResponseEntity(new Message(response), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContainerDTO> update(@PathVariable Integer id, @RequestBody ContainerDTO requestDTO) {
        if (id == null || id <= 0) {
            return new ResponseEntity(new Message("invalid id request"), HttpStatus.BAD_REQUEST);
        }
        ContainerDTO response = containerService.updateContainer(id, requestDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return new ResponseEntity(new Message("invalid id request"), HttpStatus.BAD_REQUEST);
        }
        String response = containerService.deleteContainer(id);
        return new ResponseEntity(new Message(response), HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<ContainerDTO> details(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return new ResponseEntity(new Message("invalid id request"), HttpStatus.BAD_REQUEST);
        }
        ContainerDTO response = containerService.getOneById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
