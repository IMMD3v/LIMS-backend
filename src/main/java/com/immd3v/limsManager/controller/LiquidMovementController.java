package com.immd3v.limsManager.controller;

import com.immd3v.limsManager.dto.ContainerIdDTO;
import com.immd3v.limsManager.dto.LiquidIdDTO;
import com.immd3v.limsManager.dto.RemainVolumeDTO;
import com.immd3v.limsManager.service.LiquidMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/liquidMovements")
@CrossOrigin(origins ="http://localhost:4200")
public class LiquidMovementController {
    @Autowired
    private LiquidMovementService liquidMovementService;
    @PostMapping("/captureLiquid")
    public ResponseEntity<RemainVolumeDTO> captureLiquidToMove(@RequestBody LiquidIdDTO liquidIdDTO) {
        RemainVolumeDTO response = liquidMovementService.captureLiquid(liquidIdDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/setLiquid")
    public ResponseEntity<RemainVolumeDTO> setLiquidToContainer(@RequestBody ContainerIdDTO containerIdDTO) {
        RemainVolumeDTO response = liquidMovementService.setLiquidToContainer(containerIdDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
