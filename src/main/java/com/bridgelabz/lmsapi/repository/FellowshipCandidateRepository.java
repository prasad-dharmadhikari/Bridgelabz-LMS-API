package com.bridgelabz.lmsapi.repository;

import com.bridgelabz.lmsapi.model.FellowshipCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FellowshipCandidateRepository extends JpaRepository<FellowshipCandidate, Long> {

}
