package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.FellowshipCandidateDTO;
import com.bridgelabz.lmsapi.dto.Response;

public interface IFellowshipCandidateService {
    Response joinTheCandidateToFellowshipProgram(FellowshipCandidateDTO fellowshipCandidateDTO);
}
