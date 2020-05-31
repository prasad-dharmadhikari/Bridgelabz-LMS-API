package com.bridgelabz.lmsapi.dto;

import com.bridgelabz.lmsapi.util.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class HiredCandidateDTO {
    private long id;
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
    @Builder.Default
    private String status = Status.PENDING.toString();
    private Date creatorStamp;
    private String creatorUser;
}