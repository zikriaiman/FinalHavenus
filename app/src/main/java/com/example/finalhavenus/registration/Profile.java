package com.example.finalhavenus.registration;

public class Profile {

    String birthday, number;

    public Profile(){

    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Profile(String birthday, String number){
        this.birthday = birthday;
        this.number = number;
    }
}
