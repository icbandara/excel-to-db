package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Thushan Kavinda <tkavinda@controlunion.com>
 * @since : 12/12/13/2023/2023
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessingUnitProductsDto implements Serializable {
    private int productID;
    private String name;
}
