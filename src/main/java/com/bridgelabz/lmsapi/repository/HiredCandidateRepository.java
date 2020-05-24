package com.bridgelabz.lmsapi.repository;

import com.bridgelabz.lmsapi.model.HiredCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiredCandidateRepository extends JpaRepository<HiredCandidate, Long> {
}
