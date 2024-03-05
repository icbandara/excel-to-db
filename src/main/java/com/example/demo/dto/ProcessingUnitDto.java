package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Thushan Kavinda <tkavinda@controlunion.com>
 * @since : 12/12/13/2023/2023
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessingUnitDto implements Serializable {
    private int processingID;
    private String name;
    private String address;
    private List<ProcessingUnitProductsDto> processingUnitProducts;

}
