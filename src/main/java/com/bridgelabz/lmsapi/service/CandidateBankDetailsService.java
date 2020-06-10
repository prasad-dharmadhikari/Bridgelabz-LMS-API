package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.config.ApplicationConfiguration;
import com.bridgelabz.lmsapi.dto.CandidateBankDetailsDTO;
import com.bridgelabz.lmsapi.response.Response;
import com.bridgelabz.lmsapi.model.CandidateBankDetails;
import com.bridgelabz.lmsapi.repository.CandidateBankDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to update candidate bank details
 */
@Service
public class CandidateBankDetailsService implements ICandidateBankDetailsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CandidateBankDetailsRepository candidateBankDetailsRepository;

    /**
     * @param candidateBankDetailsDTO
     * @return Bank details updation confirmation message
     */
    @Override
    public Response updateCandidateBankInfo(CandidateBankDetailsDTO candidateBankDetailsDTO) {
        candidateBankDetailsDTO.setCreatorUser(candidateBankDetailsDTO.getAccountName());
        CandidateBankDetails candidateBankDetails = modelMapper.map(candidateBankDetailsDTO, CandidateBankDetails.class);
        candidateBankDetailsRepository.save(candidateBankDetails);
        return new Response(111, ApplicationConfiguration.getMessageAccessor().getMessage("111"));
    }
}
