package com.bridgelabz.lmsapi.dto;

import com.bridgelabz.lmsapi.model.FellowshipCandidate;
import com.bridgelabz.lmsapi.util.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CandidateBankDetailsDTO {
    private long id;
    private FellowshipCandidate fellowshipCandidate;
    private String accountName;
    private long accountNumber;
    @Builder.Default
    private String isAccountNumberVerified = Status.YES.toString();
    @Pattern(regexp = "[A-Z|a-z]{4}[0][\\d]{6}$")
    private String ifscCode;
    @Builder.Default
    private String isIfscCodeVerified = Status.YES.toString();
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}")
    private String panNumber;
    @Builder.Default
    private String isPanNumberVerified = Status.YES.toString();
    @Pattern(regexp = "^\\d{4}\\s\\d{4}\\s\\d{4}$")
    private String adhaarNumber;
    @Builder.Default
    private String isAdhaarNumberVerified = Status.YES.toString();
    @Builder.Default
    private LocalDateTime creatorStamp = LocalDateTime.now();
    private String creatorUser;
}
