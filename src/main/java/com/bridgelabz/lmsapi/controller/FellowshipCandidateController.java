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

    @PostMapping("/joincandidate")
    public ResponseEntity<Response> joinCandidate(@RequestBody FellowshipCandidateDTO fellowshipCandidateDTO) {
        return ResponseEntity.ok(fellowshipCandidateService.joinTheCandidateToFellowshipProgram(fellowshipCandidateDTO));
    }

    @GetMapping("/candidatecount")
    public ResponseEntity<Response> getCandidateCount() {
        return ResponseEntity.ok(fellowshipCandidateService.getCandidateCount());
    }

    @PostMapping("/bankinfo")
    public ResponseEntity<Response> updateCandidateBankInfo(@RequestBody CandidateBankDetailsDTO candidateBankDetailsDTO) {
        return ResponseEntity.ok(candidateBankDetailsService.updateCandidateBankInfo(candidateBankDetailsDTO));
    }

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadDocuments(@RequestParam("file") MultipartFile file, @RequestParam("id") long id) throws IOException {
        return ResponseEntity.ok(candidateDocumentsService.saveDocument(file, id));
    }

    @PostMapping("/educationalinfo")
    public ResponseEntity<Response> updateCandidateQualificationInfo (@RequestBody CandidateQualificationDTO candidateQualificationDTO) {
        return ResponseEntity.ok(candidateQualificationService.updateCandidateQualificationInfo(candidateQualificationDTO));
    }

//    @GetMapping("/download/{id}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable long id) {
//        CandidateDocuments candidateDocument = candidateDocumentsService.getFile(id);
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(candidateDocument.getDocumentType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + candidateDocument.getDocumentName() + "\"")
//                .body(new ByteArrayResource(candidateDocument.getDocument()));
//    }
}
