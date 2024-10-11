package com.immd3v.limsManager.controller;

import com.immd3v.limsManager.dto.LiquidDTO;
import com.immd3v.limsManager.dto.LiquidIdDTO;
import com.immd3v.limsManager.service.LiquidMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/liquidMovements")
@CrossOrigin(origins ="http://localhost:4200")
public class LiquidMovementController {
    @Autowired
    private LiquidMovementService liquidMovementService;
    @PostMapping("/captureLiquid")
    public void captureLiquidToMove(@RequestBody LiquidIdDTO liquidIdDTO) {
        liquidMovementService.captureLiquid(liquidIdDTO);
    }
}
