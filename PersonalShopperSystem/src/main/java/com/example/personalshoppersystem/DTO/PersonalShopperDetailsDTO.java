package com.example.personalshoppersystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonalShopperDetailsDTO {

    private Integer personalShopperId;
    private String phoneNumber;
    private String briefDescription;
    private  String specialty;
    private String gender;
    private Double rating;
    private Double balance;

}
