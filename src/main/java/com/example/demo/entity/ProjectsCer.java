package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author : Udara Deshan <udaradeshan.ud@gmail.com>
 * @since : 1/5/2023
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "projects_cer")
public class ProjectsCer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cerID;
    private int proID;
    private Date expairDate;
    @Column(name = "expairDate_na" , columnDefinition = "TINYINT",nullable = false)
    private int expairDate_na;
    @Column(name = "active" , columnDefinition = "TINYINT",nullable = false)
    private int active;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="certCode",referencedColumnName = "certCode", nullable = false)
    private Certifications certifications;
}
