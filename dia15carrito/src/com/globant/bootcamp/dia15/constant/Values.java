package com.globant.bootcamp.dia15.constant;

public enum Values {
    SECURITY_TOKEN_SUPER_TOKEN("0123456789"),
    SECURITY_TOKEN_INPUT("abcdefghijklmnopqrstuvwxyz"),
    SECURITY_TOKEN_LENGTH(30),
    RESERVATION_DATE_MAX_DELTA(7),
    PERSON_DATE_MAX_DELTA(30);

    private String string;
    private int number;

    Values(String string){
        this.string = string;
    }

    Values(int number){
        this.number = number;
    }

    public String getString(){
        return string;
    }

    public int getNumber(){
        return number;
    }

}
