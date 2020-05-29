package com.bridgelabz.lmsapi.repository;

import com.bridgelabz.lmsapi.model.CandidateDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateDocumentsRepository extends JpaRepository<CandidateDocuments, Long> {

}
