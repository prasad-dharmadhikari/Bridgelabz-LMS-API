package com.bridgelabz.lmsapi.repository;

import com.bridgelabz.lmsapi.model.HiredCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HiredCandidateRepository extends JpaRepository<HiredCandidate, Long> {
    HiredCandidate findByEmail(String email);
    HiredCandidate findByStatusAndId(String status, Long id);
    List<HiredCandidate> findByStatus(String status);
}
