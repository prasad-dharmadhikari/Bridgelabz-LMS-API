package com.bridgelabz.lmsapi.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
@NamedQuery(name = "User.findByUserName", query = "select u from User u where u.userName = ?1")
@NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email = ?1")
@Table(name = "userdetails")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @JsonIgnore
    @Column(name = "Password")
    private String password;

    @Column(name = "contact_number")
    private long contactNumber;

    @Column(name = "verified")
    private String verified;

    @Column(name = "creator_stamp")
    private LocalDateTime creatorStamp;

    @Column(name = "creator_user")
    private String creatorUser;
}
