package com.bridgelabz.lmsapi.controller;

import com.bridgelabz.lmsapi.dto.FellowshipCandidateDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.service.IFellowshipCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fellowshipcandidate")
public class FellowshipCandidateController {

    @Autowired
    private IFellowshipCandidateService fellowshipCandidateService;

    @PostMapping("/jointhecandidate")
    public ResponseEntity<Response> joinCandidate(@RequestBody FellowshipCandidateDTO fellowshipCandidateDTO) {
        return ResponseEntity.ok(fellowshipCandidateService.joinTheCandidateToFellowshipProgram(fellowshipCandidateDTO));
    }

    @GetMapping("/getcandidatecount")
    public ResponseEntity<Response> getCandidateCount() {
        return ResponseEntity.ok(fellowshipCandidateService.getCandidateCount());
    }
}
