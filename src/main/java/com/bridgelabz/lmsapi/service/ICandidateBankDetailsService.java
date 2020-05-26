package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.CandidateBankDetailsDTO;
import com.bridgelabz.lmsapi.dto.Response;

public interface ICandidateBankDetailsService {
    Response updateCandidateBankInfo(CandidateBankDetailsDTO candidateBankDetailsDTO);
}
