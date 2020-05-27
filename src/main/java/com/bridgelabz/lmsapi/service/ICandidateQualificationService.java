package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.CandidateQualificationDTO;
import com.bridgelabz.lmsapi.dto.Response;

public interface ICandidateQualificationService {
    Response updateCandidateQualificationInfo(CandidateQualificationDTO candidateQualificationDTO);
}
