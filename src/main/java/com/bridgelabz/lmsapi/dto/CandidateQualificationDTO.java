package com.bridgelabz.lmsapi.dto;

import com.bridgelabz.lmsapi.model.FellowshipCandidate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class CandidateQualificationDTO {
    public long Id;
    public FellowshipCandidate fellowshipCandidate;
    public String diplomaDegreeName;
    public String isDegreeNameVerified;
    public String employeeDiscipline;
    public String isEmployeeDisciplineVerified;
    public String passingYear;
    public String isPassingYearVerified;
    public Double aggregatePercentage;
    public String isAggregatePercentageVerified;
    public Double finalYearPercentage;
    public String isFinalYearPercentageVerified;
    public String trainingInstitute;
    public String isTrainingInstituteVerified;
    public String trainingDurationMonth;
    public String isTrainingDurationMonthVerified;
    public String otherTraining;
    public String isOtherTrainingVerified;
    public LocalDateTime creatorStamp;
    public String creatorUser;
}
