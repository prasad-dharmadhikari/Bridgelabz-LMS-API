package com.bridgelabz.lmsapi.dto;

import com.bridgelabz.lmsapi.model.FellowshipCandidate;
import lombok.AccessLevel;
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
    private String isAccountNumberVerified;
    @Pattern(regexp = "[A-Z|a-z]{4}[0][\\d]{6}$")
    private String ifscCode;
    private String isIfscCodeVerified;
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}")
    private String panNumber;
    private String isPanNumberVerified;
    @Pattern(regexp = "^\\d{4}\\s\\d{4}\\s\\d{4}$")
    private String adhaarNumber;
    private String isAdhaarNumberVerified;
    private LocalDateTime creatorStamp;
    private String creatorUser;
}
