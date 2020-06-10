package com.bridgelabz.lmsapi.controller;

import com.bridgelabz.lmsapi.response.Response;
import com.bridgelabz.lmsapi.exception.LmsApiApplicationException;
import com.bridgelabz.lmsapi.model.HiredCandidate;
import com.bridgelabz.lmsapi.service.IHiredCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;

/**
 * Controller for hired candidate to save,retrieve candidate list, and view candidate profile
 */
@RestController
@RequestMapping("/hiredcandidate")
public class HiredCandidateController {
    @Autowired
    private IHiredCandidateService hiredCandidateService;

    /**
     * @param file
     * @return saved data confirmation message
     * @throws LmsApiApplicationException
     */
    @PostMapping("/save")
    public ResponseEntity<Response> saveDataInBatch(@RequestParam("file") MultipartFile file) throws LmsApiApplicationException {
        return ResponseEntity.ok(hiredCandidateService.saveDataInBatchToDatabase(file));
    }

    /**
     * @return list of hired candidates
     */
    @GetMapping("/candidates")
    public List getHiredCandidateList() {
        return hiredCandidateService.getHiredCandidateList();
    }

    /**
     * @param id
     * @return Candidate profile by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<HiredCandidate> getCandidateProfile(@PathVariable long id) {
        return ResponseEntity.ok(hiredCandidateService.getCandidateProfile(id));
    }

    /**
     * @param response
     * @param email
     * @return updated candidate status
     * @throws LmsApiApplicationException
     */
    @PutMapping("/updatestatus")
    public ResponseEntity<Response> updateCandidateStatus(@RequestParam String response, @RequestParam String email) throws LmsApiApplicationException {
        return ResponseEntity.ok(hiredCandidateService.updateCandidateStatus(response, email));
    }

    /**
     * @return Job offer notification
     * @throws MessagingException
     */
    @GetMapping("/sendjobnotification")
    public ResponseEntity<Response> sendJobOfferNotification() throws MessagingException {
        return ResponseEntity.ok(hiredCandidateService.sendJobOfferNotification());
    }
}