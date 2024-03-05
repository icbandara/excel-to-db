package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 * @author : Udara Deshan <udaradeshan.ud@gmail.com>
 * @since : 1/9/2023
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "countries")
public class Countries {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int countryID;
    private String countryName;
}
