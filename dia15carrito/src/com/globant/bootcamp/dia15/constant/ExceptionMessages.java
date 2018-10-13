package com.globant.bootcamp.dia15.constant;

public enum ExceptionMessages {
    BAD_REQUEST_PUBLISHER_IS_NULL("Publisher field can not be null."),
    BAD_REQUEST_PERSON_HAS_PRODUCTS_PUBLISHED("Person has published products; rectify this before deletion."),
    BAD_REQUEST_PRODUCT_HAS_RESERVATION_PENDING("Product has pending reservations; rectify this before deletion."),
    BAD_REQUEST_PERSON_HAS_RESERVATION_PENDING("Person has pending reservations; rectify this before deletion."),
    BAD_REQUEST_CATEGORY_NAME_MUST_BE_UNIQUE("There is already a category with that name."),
    BAD_REQUEST_PERSON_USERNAME_MUST_BE_UNIQUE("There is already a person with that username."),
    BAD_REQUEST_PRODUCT_HAS_INSUFFICIENT_STOCK("Product has insufficient stock."),

    UNAUTHORIZED_AUTHORIZATION_FAILURE("Authorization failed. Check your data."),
    UNAUTHORIZED_CREDENTIALS_REQUIRED("Access requires proper credentials. Please login or register."),
    UNAUTHORIZED_INVALID_TOKEN("Invalid token."),

    FORBIDDEN_MANIPULATION_OF_SUPER("User with 'SUPER' property can not be manipulated."),
    FORBIDDEN_MANIPULATION_OF_FIRST_CATEGORY("Uncategorized category can not be manipulated."),
    FORBIDDEN_CANNOT_LOGOUT_SUPER("User with 'SUPER' property can not logout."),
    FORBIDDEN_INSUFFICIENT_CLEARANCE("Insufficient clearance level."),
    FORBIDDEN_ACCESS_ROLE_RESTRICTED_PART1("Access is restricted to "),
    FORBIDDEN_ACCESS_ROLE_RESTRICTED_PART2(" role only."),
    FORBIDDEN_PERSON_ALREADY_LOGGED_IN("Username is already authenticated in the system."),
    FORBIDDEN_PRODUCT_IS_NOT_YOURS("Ownership of this product is not yours."),
    FORBIDDEN_RESERVATION_IS_NOT_YOURS("Ownership of this reservation is not yours."),

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