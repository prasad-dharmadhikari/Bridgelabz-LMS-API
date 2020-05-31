package com.bridgelabz.lmsapi.dto;
import lombok.*;
import java.io.Serializable;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	private String username;
	private String password;
}