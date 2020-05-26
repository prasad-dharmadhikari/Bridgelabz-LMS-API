package com.bridgelabz.lmsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity(name = "candidate_bank_details")
@Table(name = "candidate_bank_details")
public class CandidateBankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FellowshipCandidate fellowshipCandidate;
    private String accountName;
    private long accountNumber;
    private String isAccountNumberVerified;
    private String ifscCode;
    private String isIfscCodeVerified;
    private String panNumber;
    private String isPanNumberVerified;
    private String adhaarNumber;
    private String isAdhaarNumberVerified;
    private LocalDateTime creatorStamp;
    private String creatorUser;
}
