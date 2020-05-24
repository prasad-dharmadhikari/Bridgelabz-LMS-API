package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.Response;
import org.springframework.web.multipart.MultipartFile;

public interface IHiredCandidateService {
    Response saveDataInBatchToDatabase(MultipartFile file);
}
