package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.CandidateBankDetailsDTO;
import com.bridgelabz.lmsapi.response.Response;

public interface ICandidateBankDetailsService {
    Response updateCandidateBankInfo(CandidateBankDetailsDTO candidateBankDetailsDTO);
}
