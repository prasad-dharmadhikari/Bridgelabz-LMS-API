package com.bridgelabz.lmsapi.dto;

import com.bridgelabz.lmsapi.util.Status;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class UserDTO {
    @NotNull
    private long id;
    @Size(max = 100)
    @Pattern(regexp ="^[A-Z]+[A-Za-z0-9]{1,}$")
    private String firstName;
    @Size(max = 100)
    @Pattern(regexp ="^[A-Z]+[A-Za-z0-9]{1,}$")
    private String lastName;
    private String userName;
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]*.{8,}$")
    private String password;
    private long contactNumber;
    @Builder.Default
    private String verified = Status.VERIFIED.toString();
    @NotNull
    @Builder.Default
    private LocalDateTime creatorStamp = LocalDateTime.now();
    private String creatorUser;
}
