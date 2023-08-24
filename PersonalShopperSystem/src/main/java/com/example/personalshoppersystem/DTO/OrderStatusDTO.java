package com.example.personalshoppersystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatusDTO {

    private Boolean IsFound;
    private Double Price;

}
