package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.FellowshipCandidateDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.model.FellowshipCandidate;
import com.bridgelabz.lmsapi.model.HiredCandidate;
import com.bridgelabz.lmsapi.repository.FellowshipCandidateRepository;
import com.bridgelabz.lmsapi.repository.HiredCandidateRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class FellowshipCandidateService implements IFellowshipCandidateService {

    @Autowired
    private HiredCandidateRepository hiredCandidateRepository;

    @Autowired
    private FellowshipCandidateRepository fellowshipCandidateRepository;

    private ModelMapper modelMapper;

    @Autowired
    public FellowshipCandidateService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addMappings(fieldMapping);
    }

    PropertyMap<HiredCandidate, FellowshipCandidateDTO> fieldMapping = new PropertyMap<>() {
        @Override
        protected void configure() {
            map().setCreatorStamp(source.getCreatorStamp());
            map().setCreatorUser(source.getCreatorUser());
        }
    };

    @Override
    public Response joinTheCandidateToFellowshipProgram(FellowshipCandidateDTO fellowshipCandidateDTO) {
        HiredCandidate acceptedCandidate = hiredCandidateRepository.findByStatusAndId("Accepted", fellowshipCandidateDTO.getId());
        fellowshipCandidateDTO.setIsBirthDateVerified("yes");
        fellowshipCandidateDTO.setPhotoPath("verified");
        fellowshipCandidateDTO.setJoiningDate(LocalDate.now());
        fellowshipCandidateDTO.setDocumentStatus("Pending");
        fellowshipCandidateDTO.setRemark("OK");
        modelMapper.map(acceptedCandidate, fellowshipCandidateDTO);
        FellowshipCandidate fellowshipCandidate = modelMapper.map(fellowshipCandidateDTO, FellowshipCandidate.class);
        fellowshipCandidateRepository.save(fellowshipCandidate);
        return new Response(200, "Candidate Joined Successfully");
    }
}
