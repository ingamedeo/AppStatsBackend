package com.ingamedeo.entity;

public class UserPerDay {

    private Long number;
    private String day;

    public UserPerDay(Long number, String day) {
        this.number = number;
        this.day = day;
    }

    public Long getNumber() {
        return number;
    }

    public String getDay() {
        return day;
    }
}
