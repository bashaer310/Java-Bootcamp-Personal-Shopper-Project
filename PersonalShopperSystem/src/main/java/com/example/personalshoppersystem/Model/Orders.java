package com.example.personalshoppersystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Category must be not null")
    @Column(columnDefinition = "varchar(40) not null")
    private String category;

    @NotNull(message = "Product must be not null")
    @Column(columnDefinition = "varchar(40) not null")
    private String product;

    @NotNull(message = "Budget must be not null")
    @Column(columnDefinition = "double not null")
    private Double Budget;

    @Column(columnDefinition = "boolean")
    private Boolean isFound;

    @Column(columnDefinition = "double")
    private Double price;

    @Column(columnDefinition = "double")
    private Double servicePrice;

    @Column(columnDefinition = "varchar(256)")
    private String comment;

    @Column(columnDefinition = "varchar(20)")
    private String createdBy;

    @Column(columnDefinition = "datetime")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "varchar(20) default 'pending' ")
    private String status ="pending";

    @ManyToOne
    @JoinColumn(name = "customer_Id" , referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "personalShopper_id" , referencedColumnName = "id")
    @JsonIgnore
    private PersonalShopper personalShopper;



}
