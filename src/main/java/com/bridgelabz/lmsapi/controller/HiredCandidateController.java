package com.bridgelabz.lmsapi.controller;

import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.service.IHiredCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/hiredcandidate")
public class HiredCandidateController {
    @Autowired
    private IHiredCandidateService hiredCandidateService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveDataInBatch(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(hiredCandidateService.saveDataInBatchToDatabase(file));
    }
}
