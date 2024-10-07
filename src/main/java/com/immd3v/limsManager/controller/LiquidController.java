package com.immd3v.limsManager.controller;

import com.immd3v.limsManager.dto.LiquidDTO;
import com.immd3v.limsManager.service.LiquidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/liquids")
@CrossOrigin(origins ="http://localhost:4200")
public class LiquidController {
    @Autowired
    private LiquidService liquidService;

    @GetMapping("/listAll")
    public List<LiquidDTO> findAll() {
        return liquidService.listAll();
    }

    @PostMapping("/create")
    public String createOne(@RequestBody LiquidDTO liquidDTO) {
        return liquidService.saveLiquid(liquidDTO);
    }

    @PutMapping("/update/{id}")
    public LiquidDTO update(@PathVariable Integer id, @RequestBody LiquidDTO liquidDTO) {
        return liquidService.update(id, liquidDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        return liquidService.deleteLiquid(id);
    }

    @GetMapping("/details/{id}")
    public LiquidDTO detailsById(@PathVariable Integer id) {
        return liquidService.getOneById(id);
    }
}
