package com.bridgelabz.lmsapi.dto;

import com.bridgelabz.lmsapi.model.FellowshipCandidate;
import com.bridgelabz.lmsapi.util.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CandidateDocumentsDTO {
    @NotNull
    private FellowshipCandidate fellowshipCandidate;
    @NotNull
    private String documentType;
    @NotNull
    private String fileType;
    @NotNull
    private String documentName;
    @NotNull
    private String documentPath;
    @NotNull
    @Builder.Default
    private String status = Status.RECEIVED.toString();
    @NotNull
    @Builder.Default
    private LocalDateTime creatorStamp = LocalDateTime.now();
    @NotNull
    private String creatorUser;
}
