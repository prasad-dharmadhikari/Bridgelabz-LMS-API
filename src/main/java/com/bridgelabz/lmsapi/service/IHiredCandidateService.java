package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.HiredCandidateDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.model.HiredCandidate;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;

public interface IHiredCandidateService {
    Response saveDataInBatchToDatabase(MultipartFile file);
    List getHiredCandidateList();
    void save(HiredCandidateDTO hiredCandidateDTO) throws MessagingException;
    HiredCandidate getCandidateProfile(long id);
    Response updateCandidateStatus(String response, String email);
    void sendMail(HiredCandidate hiredCandidate) throws MessagingException;
    Response sendJobOfferNotification() throws MessagingException;
}
