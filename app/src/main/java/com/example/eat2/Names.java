package com.example.eat2;

public class Names {

    private String name;

    private String phoneNumber;
    private String emailId;
    public Names(){

    }

    public Names( String name,  String phoneNumber, String emailId) {

        this.name = name;

        this.phoneNumber = phoneNumber;
        this.emailId=emailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}






