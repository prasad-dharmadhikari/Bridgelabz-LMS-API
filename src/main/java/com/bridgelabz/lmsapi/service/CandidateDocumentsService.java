package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.config.ApplicationConfiguration;
import com.bridgelabz.lmsapi.dto.CandidateDocumentsDTO;
import com.bridgelabz.lmsapi.response.Response;
import com.bridgelabz.lmsapi.model.CandidateDocuments;
import com.bridgelabz.lmsapi.model.FellowshipCandidate;
import com.bridgelabz.lmsapi.repository.CandidateDocumentsRepository;
import com.bridgelabz.lmsapi.repository.FellowshipCandidateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class CandidateDocumentsService implements ICandidateDocumentsService {

    @Autowired
    private FellowshipCandidateRepository fellowshipCandidateRepository;

    @Autowired
    private CandidateDocumentsRepository candidateDocumentsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Response saveDocument(MultipartFile file, long id) throws IOException {
        CandidateDocumentsDTO candidateDocumentsDTO = new CandidateDocumentsDTO();
        candidateDocumentsDTO.setFellowshipCandidate(new FellowshipCandidate(id));
        //candidateDocumentsDTO.setDocument(file.getBytes());
        candidateDocumentsDTO.setDocumentName(file.getOriginalFilename());
        candidateDocumentsDTO.setDocumentType(file.getContentType());
        candidateDocumentsDTO.setStatus("Received");
        candidateDocumentsDTO.setCreatorStamp(LocalDateTime.now());
        candidateDocumentsDTO.setCreatorUser(fellowshipCandidateRepository.findById(id).get().getFirstName());
        CandidateDocuments candidateDocument = modelMapper.map(candidateDocumentsDTO, CandidateDocuments.class);
        candidateDocumentsRepository.save(candidateDocument);
        return new Response(110, ApplicationConfiguration.getMessageAccessor().getMessage("110"));
    }

    @Override
    public CandidateDocuments getFile(long id) {
        return candidateDocumentsRepository.findById(id).get();
    }
}
