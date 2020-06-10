package com.bridgelabz.lmsapi.controller;

import com.bridgelabz.lmsapi.dto.CandidateBankDetailsDTO;
import com.bridgelabz.lmsapi.dto.CandidateQualificationDTO;
import com.bridgelabz.lmsapi.dto.FellowshipCandidateDTO;
import com.bridgelabz.lmsapi.response.Response;
import com.bridgelabz.lmsapi.service.ICandidateBankDetailsService;
import com.bridgelabz.lmsapi.service.ICandidateDocumentsService;
import com.bridgelabz.lmsapi.service.ICandidateQualificationService;
import com.bridgelabz.lmsapi.service.IFellowshipCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller for fellowship candidate
 */
@RestController
@RequestMapping("/fellowshipcandidate")
public class FellowshipCandidateController {

    @Autowired
    private IFellowshipCandidateService fellowshipCandidateService;

    @Autowired
    private ICandidateBankDetailsService candidateBankDetailsService;

    @Autowired
    private ICandidateDocumentsService candidateDocumentsService;

    @Autowired
    private ICandidateQualificationService candidateQualificationService;

    /**
     * @param fellowshipCandidateDTO
     * @return Joining message to candidate
     */
    @PostMapping("/joincandidate")
    public ResponseEntity<Response> joinCandidate(@RequestBody FellowshipCandidateDTO fellowshipCandidateDTO) {
        return ResponseEntity.ok(fellowshipCandidateService.joinTheCandidateToFellowshipProgram(fellowshipCandidateDTO));
    }

    /**
     * @return Candidate count in fellowship program
     */
    @GetMapping("/candidatecount")
    public ResponseEntity<Response> getCandidateCount() {
        return ResponseEntity.ok(fellowshipCandidateService.getCandidateCount());
    }

    /**
     * @param candidateBankDetailsDTO
     * @return Confirmation of bank details info
     */
    @PostMapping("/bankinfo")
    public ResponseEntity<Response> updateCandidateBankInfo(@RequestBody CandidateBankDetailsDTO candidateBankDetailsDTO) {
        return ResponseEntity.ok(candidateBankDetailsService.updateCandidateBankInfo(candidateBankDetailsDTO));
    }

    /**
     * @param id
     * @param file
     * @param type
     * @return Files uploaded successfully
     * @throws IOException
     */
    @PostMapping("/upload/{id}")
    public ResponseEntity<Response> uploadDocuments(@PathVariable long id, @RequestParam("file") MultipartFile file,
                                                    @RequestParam("type") String type) throws IOException {
        return ResponseEntity.ok(candidateDocumentsService.saveDocument(file, id, type));
    }

    /**
     * @param candidateQualificationDTO
     * @return updated candidate qualification info
     */
    @PostMapping("/educationalinfo")
    public ResponseEntity<Response> updateCandidateQualificationInfo (@RequestBody CandidateQualificationDTO candidateQualificationDTO) {
        return ResponseEntity.ok(candidateQualificationService.updateCandidateQualificationInfo(candidateQualificationDTO));
    }
}
