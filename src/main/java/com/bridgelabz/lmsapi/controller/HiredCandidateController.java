package com.bridgelabz.lmsapi.controller;

import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.model.HiredCandidate;
import com.bridgelabz.lmsapi.service.IHiredCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/hiredcandidate")
public class HiredCandidateController {
    @Autowired
    private IHiredCandidateService hiredCandidateService;

    @PostMapping("/save")
    public ResponseEntity<Response> saveDataInBatch(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(hiredCandidateService.saveDataInBatchToDatabase(file));
    }

    @GetMapping("/candidates")
    public List getHiredCandidateList() {
        return hiredCandidateService.getHiredCandidateList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HiredCandidate> getCandidateProfile(@PathVariable long id) {
        return ResponseEntity.ok(hiredCandidateService.getCandidateProfile(id));
    }

    @PutMapping("/updatestatus")
    public ResponseEntity<Response> updateCandidateStatus(@RequestParam String response, @RequestParam String email) {
        return ResponseEntity.ok(hiredCandidateService.updateCandidateStatus(response, email));
    }

    @GetMapping("/sendjobnotification")
    public ResponseEntity<Response> sendJobOfferNotification() throws MessagingException {
        return ResponseEntity.ok(hiredCandidateService.sendJobOfferNotification());
    }

}