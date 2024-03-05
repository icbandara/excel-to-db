package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.List;

/**
 * @author : Udara Deshan <udaradeshan.ud@gmail.com>
 * @since : 10/31/2022
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proID", nullable = false)
    private int proID;
    @Column(name = "proCode",nullable = false)
    private int proCode;
    @Column(name = "proName",nullable = false)
    private String proName;
    @Column(name = "proAddress")
    private String proAddress;
    @Column(name = "city")
    private String city;
    @Column(name = "contactPer")
    private String contactPer;
    @Column(name = "contactNo")
    private String  contactNo;
    @Column(name = "tp")
    private String  tp;
    @Column(name = "fax")
    private String  fax;
    @Column(name = "email")
    private String email;
    @Builder.Default
    @Column(name = "activ" , columnDefinition = "TINYINT",nullable = false)
    private Integer  activ=0;
    @Column(name = "username",nullable = false)
    private String username;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="Asia/Kolkata")
    @Column(name = "systimeStamp",insertable = false,updatable = false)
    @CreationTimestamp
    private Date systimeStamp;
    @ManyToOne
    @JoinColumn(name = "countryID",referencedColumnName = "countryID")
    private Countries countryID;
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name="proID",referencedColumnName = "proID", nullable = false,insertable = false,updatable = false)
    private List<ProjectsCer> projectsCerList;
}
