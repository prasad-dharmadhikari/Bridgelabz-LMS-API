package com.bridgelabz.lmsapi.repository;

import com.bridgelabz.lmsapi.model.CandidateBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateBankDetailsRepository extends JpaRepository<CandidateBankDetails, Long> {

}
