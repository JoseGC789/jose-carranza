package com.globant.bootcamp.dia15.constants;

public enum ExceptionMessages {
    BAD_REQUEST_PUBLISHER_IS_NULL("Publisher field can not be null."),
    BAD_REQUEST_PERSON_CHECK_YOUR_DATA("Can not authenticate user. Check your data."),
    BAD_REQUEST_PERSON_HAS_PRODUCTS_PUBLISHED("Person has products published; rectify this before deletion."),
    BAD_REQUEST_PERSON_HAS_RESERVATION_PENDING("Person has reservations pending; rectify this before deletion."),

    FORBIDDEN_MANIPULATION_OF_SUPER("User with 'SUPER' property can not be manipulated."),
    FORBIDDEN_CANNOT_LOGOUT_SUPER("User with 'SUPER' property can not logout."),
    FORBIDDEN_INSUFFICIENT_CLEARANCE("Insufficient clearance."),
    FORBIDDEN_UNMATCHED_TOKEN("Unmatched token. Please re-login."),
    FORBIDDEN_PERSON_ALREADY_LOGGED_IN("Username is already authenticated in the system."),

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