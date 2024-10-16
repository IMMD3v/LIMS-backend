package com.immd3v.limsManager.controller;

import com.immd3v.limsManager.dto.LiquidDTO;
import com.immd3v.limsManager.message.Message;
import com.immd3v.limsManager.service.LiquidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/liquids")
@CrossOrigin(origins ="http://localhost:4200")
public class LiquidController {
    @Autowired
    private LiquidService liquidService;

    @GetMapping("/listAll")
    public ResponseEntity<List<LiquidDTO>> findAll() {
        List<LiquidDTO> response = liquidService.listAll();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOne(@RequestBody LiquidDTO liquidDTO) {
        String response = liquidService.saveLiquid(liquidDTO);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LiquidDTO> update(@PathVariable Integer id, @RequestBody LiquidDTO liquidDTO) {
        if (id == null || id <= 0) {
            return new ResponseEntity(new Message("invalid id request"), HttpStatus.BAD_REQUEST);
        }
        LiquidDTO response = liquidService.update(id, liquidDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return new ResponseEntity(new Message("invalid id request"), HttpStatus.BAD_REQUEST);
        }
        String response = liquidService.deleteLiquid(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<LiquidDTO> detailsById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return new ResponseEntity(new Message("invalid id request"), HttpStatus.BAD_REQUEST);
        }
        LiquidDTO response = liquidService.getOneById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
