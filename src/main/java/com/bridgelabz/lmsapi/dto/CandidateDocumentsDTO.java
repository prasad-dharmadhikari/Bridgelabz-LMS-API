package com.bridgelabz.lmsapi.dto;

import com.bridgelabz.lmsapi.model.FellowshipCandidate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.StringReader;
import java.time.LocalDateTime;


@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CandidateDocumentsDTO {
    @NotNull
    private FellowshipCandidate fellowshipCandidate;
    @NotNull
    private String documentType;
    @NotNull
    private String documentName;
    @NotNull
    private byte[] document;
    @NotNull
    private String status;
    @NotNull
    private LocalDateTime creatorStamp;
    @NotNull
    private String creatorUser;
}
