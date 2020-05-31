package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.response.Response;
import com.bridgelabz.lmsapi.model.CandidateDocuments;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ICandidateDocumentsService {
    Response saveDocument(MultipartFile file, long id) throws IOException;
    CandidateDocuments getFile(long id);
}
