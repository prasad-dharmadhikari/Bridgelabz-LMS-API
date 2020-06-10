package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.config.ApplicationConfiguration;
import com.bridgelabz.lmsapi.dto.CandidateQualificationDTO;
import com.bridgelabz.lmsapi.response.Response;
import com.bridgelabz.lmsapi.model.CandidateQualification;
import com.bridgelabz.lmsapi.repository.CandidateQualificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service to update candidate qualification details
 */
@Service
public class CandidateQualificationService implements ICandidateQualificationService {

    @Autowired
    private CandidateQualificationRepository candidateQualificationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Response updateCandidateQualificationInfo(CandidateQualificationDTO candidateQualificationDTO) {
        candidateQualificationDTO.setCreatorUser("Admin");
        CandidateQualification candidateQualification = modelMapper.map(candidateQualificationDTO,CandidateQualification.class);
        candidateQualificationRepository.save(candidateQualification);
        return new Response(108, ApplicationConfiguration.getMessageAccessor().getMessage("108"));
    }
}
