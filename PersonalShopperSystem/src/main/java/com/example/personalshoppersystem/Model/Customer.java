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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Name must be not null")
    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "[a-zA-Z]+" , message = "Name able letters only")
    @Size(min = 2 ,message = "Name must be more than 2 letters" )
    private String name;

    @NotNull(message = "Email must be not null")
    @Email(message = "Email must be a valid")
    @Column(columnDefinition = "varchar(50) not null UNIQUE")
    private String email;

    @NotNull(message = "Password should be not null")
    @Pattern(regexp ="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d\\s]{1,}$" , message = "Password have characters and digits")
    @Column(columnDefinition = "varchar(20) not null")
    @Size(min = 8 ,message = "Password size must equal or more than 8")
    private String password;

    @NotNull(message = "Username should be not null")
    @Column(columnDefinition = "varchar(20) not null UNIQUE")
    @Size(min = 4 ,message = "Name must be more than 4")
    private String username;

    @NotNull(message = "Phone Number should be not null")
    @Pattern(regexp = "^(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$", message = "The phone number must be a valid Saudi number")
    @Column(columnDefinition = "varchar(16) not null UNIQUE")
    private String phoneNumber;

    @NotEmpty(message = "Gender must be not empty!")
    @Pattern(regexp ="\\b(male|female)\\b" , message = "Gender must be male or female")
    @Column(columnDefinition = "varchar(6) not null")
    private String gender;

    @NotNull(message = "City must be not null")
    @Column(columnDefinition = "varchar(40) not null")
    private String city;

    @NotNull(message = "Address must be not null")
    @Column(columnDefinition = "varchar(256) not null")
    private String address;

    @NotNull(message = "Age must be not null")
    @Positive(message = "Age must be positive number")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @PositiveOrZero(message = "Use positive number")
    @Column(columnDefinition = "int default 0")
    private Integer loyaltyPoints=0;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Orders> orders;

}
