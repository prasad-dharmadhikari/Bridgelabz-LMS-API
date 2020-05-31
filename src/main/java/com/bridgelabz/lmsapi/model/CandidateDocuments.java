package com.bridgelabz.lmsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity(name = "candidate_documents")
@Table(name = "candidate_documents")
public class CandidateDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private FellowshipCandidate fellowshipCandidate;
    @NotNull
    private String documentType;
    @NotNull
    private String fileType;
    @NotNull
    private String documentName;
    @NotNull
    private String status;
    @NotNull
    private LocalDateTime creatorStamp;
    @NotNull
    private String creatorUser;
}
