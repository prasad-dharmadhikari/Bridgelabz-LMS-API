package com.bridgelabz.lmsapi.controller;

import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.model.CandidateDocuments;
import com.bridgelabz.lmsapi.service.ICandidateDocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/documents")
public class CandidateDocumentsController {
    @Autowired
    private ICandidateDocumentsService candidateDocumentsService;

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadDocuments(@RequestParam("file") MultipartFile file, @RequestParam("id") long id) throws IOException {
        return ResponseEntity.ok(candidateDocumentsService.saveDocument(file, id));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable long id) {
        CandidateDocuments candidateDocument = candidateDocumentsService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(candidateDocument.getDocumentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + candidateDocument.getDocumentName() + "\"")
                .body(new ByteArrayResource(candidateDocument.getDocument()));
    }
}
