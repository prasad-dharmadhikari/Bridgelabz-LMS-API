package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.CandidateQualificationDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.model.CandidateQualification;
import com.bridgelabz.lmsapi.repository.CandidateQualificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CandidateQualificationService implements ICandidateQualificationService {

    @Autowired
    private CandidateQualificationRepository candidateQualificationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Response updateCandidateQualificationInfo(CandidateQualificationDTO candidateQualificationDTO) {
        candidateQualificationDTO.setIsAggregatePercentageVerified("Yes");
        candidateQualificationDTO.setIsDegreeNameVerified("Yes");
        candidateQualificationDTO.setIsEmployeeDisciplineVerified("Yes");
        candidateQualificationDTO.setIsFinalYearPercentageVerified("Yes");
        candidateQualificationDTO.setIsOtherTrainingVerified("Yes");
        candidateQualificationDTO.setIsTrainingInstituteVerified("Yes");
        candidateQualificationDTO.setIsPassingYearVerified("Yes");
        candidateQualificationDTO.setIsTrainingDurationMonthVerified("Yes");
        candidateQualificationDTO.setCreatorStamp(LocalDateTime.now());
        candidateQualificationDTO.setCreatorUser("Admin");
        CandidateQualification candidateQualification = modelMapper.map(candidateQualificationDTO,CandidateQualification.class);
        candidateQualificationRepository.save(candidateQualification);
        return new Response(200, "Candidate Qualification Details Updated Successfully");
    }
}
