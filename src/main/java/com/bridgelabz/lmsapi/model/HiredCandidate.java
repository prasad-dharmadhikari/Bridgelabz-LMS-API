package com.bridgelabz.lmsapi.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity(name = "hired_candidate")
@NamedQuery(name = "HiredCandidate.findByEmail", query = "select h from hired_candidate h where h.email = ?1")
@NamedQuery(name = "HiredCandidate.findByStatus", query = "select h from hired_candidate h where h.status = ?1")
@Table(name = "hired_candidate")
public class HiredCandidate {
    @Id
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
    private String status;
    private Date creatorStamp;
    private String creatorUser;
}
