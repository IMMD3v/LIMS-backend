package com.immd3v.limsManager.controller;

import com.immd3v.limsManager.dto.AnalysisRequestDTO;
import com.immd3v.limsManager.service.AnalysisRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analysis")
@CrossOrigin(origins ="http://localhost:4200")
public class AnalysisRequestController {
    @Autowired
    private AnalysisRequestService analysisRequestService;

    @GetMapping("/listAll")
    public List<AnalysisRequestDTO> listAll() {
        return analysisRequestService.getAllAnalysis();
    }
    @PostMapping("/create")
    public String createOne(@RequestBody AnalysisRequestDTO analysisRequestDTO) {
        return analysisRequestService.createOne(analysisRequestDTO);
    }
    @PutMapping("/deleteById/{id}")
    public String deleteRequest(int id) {
        return analysisRequestService.deleteRequest(id);
    }
}
