package com.bridgelabz.lmsapi.exception;

public class LmsApiApplicationException extends Exception {

    private static final long serialVersionUID = 5776681206288518465L;
    private String message;

    public enum exceptionType {
        USER_NOT_FOUND, INVALID_EMAIL_ID, INVALID_PASSWORD,
        INVALID_TOKEN, DATA_NOT_FOUND, INVALID_ID;
    }

    private exceptionType type;

    public LmsApiApplicationException(exceptionType type, String message) {
        super(message);
        this.message = message;
        this.type = type;
    }
}
