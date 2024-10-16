package com.immd3v.limsManager.controller;

import com.immd3v.limsManager.dto.AnalysisRequestDTO;
import com.immd3v.limsManager.dto.ContainerDTO;
import com.immd3v.limsManager.message.Message;
import com.immd3v.limsManager.service.AnalysisRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analysis")
@CrossOrigin(origins ="http://localhost:4200")
public class AnalysisRequestController {
    @Autowired
    private AnalysisRequestService analysisRequestService;

    @GetMapping("/listAll")
    public ResponseEntity<List<AnalysisRequestDTO>> listAll() {
        List<AnalysisRequestDTO> response = analysisRequestService.getAllAnalysis();
        return new ResponseEntity(response, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createOne(@RequestBody AnalysisRequestDTO analysisRequestDTO) {
        String response = analysisRequestService.createOne(analysisRequestDTO);
        return new ResponseEntity(new Message(response), HttpStatus.OK);
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return new ResponseEntity(new Message("invalid id request"), HttpStatus.BAD_REQUEST);
        }
        String response = analysisRequestService.deleteRequest(id);
        return new ResponseEntity(new Message(response), HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<AnalysisRequestDTO> details(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return new ResponseEntity(new Message("invalid id request"), HttpStatus.BAD_REQUEST);
        }
        AnalysisRequestDTO response = analysisRequestService.getOneById(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AnalysisRequestDTO> update(@PathVariable Integer id, @RequestBody AnalysisRequestDTO requestDTO) {
        if (id == null || id <= 0) {
            return new ResponseEntity(new Message("invalid id request"), HttpStatus.BAD_REQUEST);
        }
        AnalysisRequestDTO response = analysisRequestService.update(id, requestDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
