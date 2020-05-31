package com.bridgelabz.lmsapi.dto;

import com.bridgelabz.lmsapi.util.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class FellowshipCandidateDTO {
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
    @Builder.Default
    private String isBirthDateVerified = Status.YES.toString();
    private String parentName;
    private String parentOccupation;
    private long parentMobileNumber;
    private int parentAnnualSalary;
    private String localAddress;
    private String permanentAddress;
    @Builder.Default
    private String photoPath = Status.VERIFIED.toString();
    @Builder.Default
    private LocalDate joiningDate = LocalDate.now();
    @Builder.Default
    private String documentStatus = Status.PENDING.toString();
    @Builder.Default
    private String remark = Status.OK.toString();
    private Date creatorStamp;
    private String creatorUser;
}
