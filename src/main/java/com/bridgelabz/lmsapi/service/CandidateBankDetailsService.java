package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.CandidateBankDetailsDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.model.CandidateBankDetails;
import com.bridgelabz.lmsapi.repository.CandidateBankDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CandidateBankDetailsService implements ICandidateBankDetailsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateBankDetailsRepository candidateBankDetailsRepository;

    @Override
    public Response updateCandidateBankInfo(CandidateBankDetailsDTO candidateBankDetailsDTO) {
        candidateBankDetailsDTO.setIsAccountNumberVerified("Yes");
        candidateBankDetailsDTO.setIsAdhaarNumberVerified("Yes");
        candidateBankDetailsDTO.setIsIfscCodeVerified("Yes");
        candidateBankDetailsDTO.setIsPanNumberVerified("Yes");
        candidateBankDetailsDTO.setCreatorStamp(LocalDateTime.now());
        candidateBankDetailsDTO.setCreatorUser(candidateBankDetailsDTO.getAccountName());
        CandidateBankDetails candidateBankDetails = modelMapper.map(candidateBankDetailsDTO, CandidateBankDetails.class);
        candidateBankDetailsRepository.save(candidateBankDetails);
        return new Response(200, "Bank Details Updated Successfully");
    }
}
