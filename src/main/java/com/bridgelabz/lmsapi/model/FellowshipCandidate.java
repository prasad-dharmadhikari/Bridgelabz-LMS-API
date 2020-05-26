package com.bridgelabz.lmsapi.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity(name = "fellowship_candidate")
@Table(name = "fellowship_candidate")
public class FellowshipCandidate {
    @Id
    private long candidateId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String hiredCity;
    private String degree;
    private Date hiredDate;
    private long mobileNumber;
    private long permanentPincode;
    private String hiredLab;
    private String attitude;
    private String communicationRemark;
    private String knowledgeRemark;
    private String aggregateRemark;
    private String status;
    private Date birthDate;
    private String isBirthDateVerified;
    private String parentName;
    private String parentOccupation;
    private long parentMobileNumber;
    private int parentAnnualSalary;
    private String localAddress;
    private String permanentAddress;
    private String photoPath;
    private LocalDate joiningDate;
    private String documentStatus;
    private String remark;
    private Date creatorStamp;
    private String creatorUser;
}
