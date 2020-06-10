package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.config.ApplicationConfiguration;
import com.bridgelabz.lmsapi.dto.FellowshipCandidateDTO;
import com.bridgelabz.lmsapi.response.Response;
import com.bridgelabz.lmsapi.model.FellowshipCandidate;
import com.bridgelabz.lmsapi.model.HiredCandidate;
import com.bridgelabz.lmsapi.repository.FellowshipCandidateRepository;
import com.bridgelabz.lmsapi.repository.HiredCandidateRepository;
import com.bridgelabz.lmsapi.util.Status;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to join the candidates to fellowship program and get candidate count
 */
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

    /**
     * @param fellowshipCandidateDTO
     * @return Candidate joined successfully
     */
    @Override
    public Response joinTheCandidateToFellowshipProgram(FellowshipCandidateDTO fellowshipCandidateDTO) {
        HiredCandidate acceptedCandidate = hiredCandidateRepository.findByStatusAndId(Status.ACCEPTED.toString(), fellowshipCandidateDTO.getCandidateId());
        modelMapper.map(acceptedCandidate, fellowshipCandidateDTO);
        FellowshipCandidate fellowshipCandidate = modelMapper.map(fellowshipCandidateDTO, FellowshipCandidate.class);
        fellowshipCandidateRepository.save(fellowshipCandidate);
        return new Response(107, ApplicationConfiguration.getMessageAccessor().getMessage("107"));
    }

    /**
     * @return No of candidates in fellowship program
     */
    @Override
    public Response getCandidateCount() {
        return new Response(109,
                ApplicationConfiguration.getMessageAccessor().getMessage("109") +
                        fellowshipCandidateRepository.count());
    }
}
