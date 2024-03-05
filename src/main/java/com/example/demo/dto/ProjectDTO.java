package com.example.demo.dto;

import com.example.demo.entity.Countries;
import com.example.demo.entity.ProjectsCer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * @author : Udara Deshan <udaradeshan.ud@gmail.com>
 * @since : 10/31/2022
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {
    private int proID;
    private int proCode;
    private String proName;
    private String proAddress;
    private String city;
    private String contactPer;
    private String contactNo;
    private String tp;
    private String fax;
    private String email;
    private int activ;
    private String username;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="Asia/Kolkata")
    private Date systimeStamp;
    private Countries countryID;
    private List<ProjectsCer> projectsCerList;
    private List<ProcessingUnitDto> processingUnits;
}
