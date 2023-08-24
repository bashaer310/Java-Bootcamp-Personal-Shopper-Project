package com.example.personalshoppersystem.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class PersonalShopper {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Name must not be empty")
    @Pattern(regexp = "[a-zA-Z]+",message = "Name must be contains only characters")
    @Column(columnDefinition = "varchar(20) not null check(name REGEXP '[a-zA-Z]+')")
    private String name;

    @NotEmpty(message = "Username must not be empty")
    //unique
    @Size(min = 4, message = "Username length must be more than 3")
    @Column(columnDefinition = "varchar(30) unique not null check(LENGTH(username)>=4)")
    private String username;


    @NotEmpty(message = "Email must not be empty")
    //unique
    @Email(message = "Email must be a valid format")
    @Column(columnDefinition = "varchar(30) not null CHECK(email REGEXP '([a-zA-Z0-9]{6,})(@)([a-zA-Z]{5,})(\\.)([a-zA-Z]{2,})')")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8, message = "Password length must be more than 8")
    @Pattern(regexp = "[A-Za-z0-9]{8,}", message = "Password must be contains characters and digits")
    @Column(columnDefinition = "varchar(30) not null CHECK(password REGEXP '[A-Za-z0-9]{8,}')")
    private String password;

    @NotEmpty(message = "City must not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String city;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "personalShopper")
    @PrimaryKeyJoinColumn
    private PersonalShopperDetails personalShopperDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalShopper")
    private Set<Orders> orders;

}
