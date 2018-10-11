package com.globant.bootcamp.dia15.constants;

public enum Values {
    SECURITY_TOKEN_SUPER_VALUE("0123456789"),
    SECURITY_TOKEN_INPUT("abcdefghijklmnopqrstuvwxyz"),
    SECURITY_TOKEN_LENGTH(30);

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
