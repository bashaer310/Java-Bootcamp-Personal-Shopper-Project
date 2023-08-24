package com.example.personalshoppersystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersonalShopperDetails {


    @Id
    private Integer id;

    @NotEmpty(message = "Phone number must not be empty")
    @Pattern(regexp = "^(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$",message = "Phone number must be a valid format")
    //unique
    @Column(columnDefinition = "varchar(17) not null")
    private String phoneNumber;

    @NotEmpty(message = "Brief description must not be empty")
    @Size(min = 15, message = "Brief description length must be more than 16")
    @Column(columnDefinition = "varchar(256) not null check(LENGTH(brief_description)>=15)")
    private String briefDescription;

    @NotEmpty(message = "Specialty must not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private  String specialty;

    @NotEmpty(message = "Gender must not be empty")
    @Pattern(regexp = "male|female", message = "Gender must be male or female ")
    @Column(columnDefinition = "varchar(6) not null CHECK(gender='male' or gender='female')")
    private String gender;

    @PositiveOrZero
    @Column(columnDefinition = "double")
    private Double rating;

    @PositiveOrZero
    @Column(columnDefinition = "double")
    private Double balance;

    @OneToOne
    @MapsId
    @JsonIgnore
    private PersonalShopper personalShopper;

}
