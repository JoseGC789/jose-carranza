package com.globant.bootcamp.dia15.constants;

public enum ExceptionMessages {
    BAD_REQUEST_PUBLISHER_IS_NULL("Publisher field cannot be null."),

    UNAUTHORIZED_PERSON_ALREADY_LOGGED_IN("Username is already logged in the system."),
    UNAUTHORIZED_PERSON_CHECK_YOUR_DATA("Failed to authenticate user. Check your data."),

    FORBIDDEN_MANIPULATION_SUPER("User with 'SUPER' property can not be manipulated"),
    FORBIDDEN_CANNOT_LOGOUT_SUPER("User with 'SUPER' property can not be logged out."),
    FORBIDDEN_INSUFFICIENT_CLEARANCE("Insufficient clearance."),
    FORBIDDEN_UNMATCHED_TOKEN("Unmatched token. Please re-login."),

    NOT_FOUND_CATEGORY("Category does not exist"),
    NOT_FOUND_PERSON("Person does not exist"),
    NOT_FOUND_PRODUCT("Product does not exist"),
    NOT_FOUND_RESERVATION("Reservation does not exist");

    private String string;

    ExceptionMessages(String string){
        this.string = string;
    }

    public String getString(){
        return string;
    }

}