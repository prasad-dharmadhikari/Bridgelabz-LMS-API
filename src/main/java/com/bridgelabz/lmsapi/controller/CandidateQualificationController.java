package com.bridgelabz.lmsapi.controller;

import com.bridgelabz.lmsapi.dto.CandidateQualificationDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.service.ICandidateQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidatequalification")
public class CandidateQualificationController {

    @Autowired
    private ICandidateQualificationService candidateQualificationService;

    @PostMapping("/educationalinfo")
    public ResponseEntity<Response> updateCandidateQualificationInfo (@RequestBody CandidateQualificationDTO candidateQualificationDTO) {
        return ResponseEntity.ok(candidateQualificationService.updateCandidateQualificationInfo(candidateQualificationDTO));
    }
}
