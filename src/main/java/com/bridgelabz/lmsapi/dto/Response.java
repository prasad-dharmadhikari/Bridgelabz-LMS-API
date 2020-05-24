package com.bridgelabz.lmsapi.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@AllArgsConstructor
public class Response {
    int status;
    String message;
}
