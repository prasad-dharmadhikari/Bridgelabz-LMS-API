package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.config.ApplicationConfiguration;
import com.bridgelabz.lmsapi.dto.CandidateDocumentsDTO;
import com.bridgelabz.lmsapi.response.Response;
import com.bridgelabz.lmsapi.model.CandidateDocuments;
import com.bridgelabz.lmsapi.model.FellowshipCandidate;
import com.bridgelabz.lmsapi.repository.CandidateDocumentsRepository;
import com.bridgelabz.lmsapi.repository.FellowshipCandidateRepository;
import com.bridgelabz.lmsapi.util.Status;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Service to upload the candidate documents
 */
@Service
public class CandidateDocumentsService implements ICandidateDocumentsService {

    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap
            ("cloud_name", System.getenv().get("cloudinary.cloud_name"),
                    "api_key", System.getenv().get("cloudinary.api_key"),
                    "api_secret", System.getenv().get("cloudinary.api_secret")));

    @Autowired
    private FellowshipCandidateRepository fellowshipCandidateRepository;

    @Autowired
    private CandidateDocumentsRepository candidateDocumentsRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * @param file
     * @param id
     * @param type
     * @return Documents uploaded successfully
     * @throws IOException
     */
    @Override
    public Response saveDocument(MultipartFile file, long id, String type) throws IOException {
        CandidateDocumentsDTO candidateDocumentsDTO = new CandidateDocumentsDTO();
        candidateDocumentsDTO.setFellowshipCandidate(new FellowshipCandidate(id));
        candidateDocumentsDTO.setDocumentName(file.getOriginalFilename());
        candidateDocumentsDTO.setDocumentType(type);
        candidateDocumentsDTO.setDocumentPath(uploadFile(file));
        candidateDocumentsDTO.setFileType(file.getContentType());
        candidateDocumentsDTO.setStatus(Status.RECEIVED.toString());
        candidateDocumentsDTO.setCreatorUser(fellowshipCandidateRepository.findById(id).get().getFirstName());
        CandidateDocuments candidateDocument = modelMapper.map(candidateDocumentsDTO, CandidateDocuments.class);
        candidateDocumentsRepository.save(candidateDocument);
        return new Response(110, ApplicationConfiguration.getMessageAccessor().getMessage("110"));
    }

    /**
     * @param file
     * @return uploaded file url
     * @throws IOException
     */
    private String uploadFile(MultipartFile file) throws IOException {
        File fileToUpload = convertMultipartToFile(file);
        Map uploadResult = cloudinary.uploader().upload(fileToUpload, ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }

    /**
     * @param file
     * @return converted file
     * @throws IOException
     */
    private File convertMultipartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    /**
     * @param id
     * @return document by id
     */
    @Override
    public CandidateDocuments getFile(long id) {
        return candidateDocumentsRepository.findById(id).get();
    }
}
