package com.hellofresh.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {
    private String email;
    private String name;
    private String surname;
    private String password;
    private String birthDate;
    private String birthMonth;
    private String birthYear;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postCode;
    private String other;
    private String phone;
    private String mobile;
    private String alias;

}
