package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.HiredCandidateDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.model.HiredCandidate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHiredCandidateService {
    Response saveDataInBatchToDatabase(MultipartFile file);
    List getHiredCandidateList();
    void save(HiredCandidateDTO hiredCandidateDTO);
    HiredCandidate getCandidateProfile(long id);
}
