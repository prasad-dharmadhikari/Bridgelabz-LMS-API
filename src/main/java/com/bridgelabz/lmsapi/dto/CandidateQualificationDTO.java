package com.bridgelabz.lmsapi.dto;

import com.bridgelabz.lmsapi.model.FellowshipCandidate;
import com.bridgelabz.lmsapi.util.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CandidateQualificationDTO {
    public long Id;
    public FellowshipCandidate fellowshipCandidate;
    public String diplomaDegreeName;
    @Builder.Default
    public String isDegreeNameVerified = Status.YES.toString();
    public String employeeDiscipline;
    @Builder.Default
    public String isEmployeeDisciplineVerified = Status.YES.toString();
    public String passingYear;
    @Builder.Default
    public String isPassingYearVerified = Status.YES.toString();
    public Double aggregatePercentage;
    @Builder.Default
    public String isAggregatePercentageVerified = Status.YES.toString();
    public Double finalYearPercentage;
    @Builder.Default
    public String isFinalYearPercentageVerified = Status.YES.toString();
    public String trainingInstitute;
    @Builder.Default
    public String isTrainingInstituteVerified = Status.YES.toString();
    public String trainingDurationMonth;
    @Builder.Default
    public String isTrainingDurationMonthVerified = Status.YES.toString();
    public String otherTraining;
    @Builder.Default
    public String isOtherTrainingVerified = Status.YES.toString();
    @Builder.Default
    public LocalDateTime creatorStamp = LocalDateTime.now();
    public String creatorUser;
}
