package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.HiredCandidateDTO;
import com.bridgelabz.lmsapi.response.Response;
import com.bridgelabz.lmsapi.exception.LmsApiApplicationException;
import com.bridgelabz.lmsapi.model.HiredCandidate;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;

public interface IHiredCandidateService {
    Response saveDataInBatchToDatabase(MultipartFile file) throws LmsApiApplicationException;
    List getHiredCandidateList();
    void save(HiredCandidateDTO hiredCandidateDTO) throws MessagingException, LmsApiApplicationException;
    HiredCandidate getCandidateProfile(long id);
    Response updateCandidateStatus(String response, String email) throws LmsApiApplicationException;
    void sendMail(HiredCandidate hiredCandidate) throws MessagingException;
    Response sendJobOfferNotification() throws MessagingException;
}
