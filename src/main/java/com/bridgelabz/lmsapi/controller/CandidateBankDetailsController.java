package com.bridgelabz.lmsapi.controller;

import com.bridgelabz.lmsapi.dto.CandidateBankDetailsDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.service.ICandidateBankDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidatebank")
public class CandidateBankDetailsController {

    @Autowired
    private ICandidateBankDetailsService candidateBankDetailsService;

    @PostMapping("/bankinfo")
    public ResponseEntity<Response> updateCandidateBankInfo(@RequestBody CandidateBankDetailsDTO candidateBankDetailsDTO) {
        return ResponseEntity.ok(candidateBankDetailsService.updateCandidateBankInfo(candidateBankDetailsDTO));
    }
}
