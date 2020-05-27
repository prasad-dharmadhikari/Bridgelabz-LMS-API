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
@Entity(name = "candidate_qualification")
@Table(name = "candidate_qualification")
public class CandidateQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long Id;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
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
