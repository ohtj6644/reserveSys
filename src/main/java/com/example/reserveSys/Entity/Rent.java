package com.example.reserveSys.Entity;


import com.example.reserveSys.Entity.User.AddUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int id;

    private String rentUserName;

    private Integer phoneNo;


    private String bookNo;

    private String bookName;

    //대여일
    @Column(name = "rentDate")
    private LocalDate rentDate;

    //대여만료예정일
    @Column(name = "expiryDate")
    private LocalDate expiryDate;

    //반납일
    @Column(name = "completeDate")
    private LocalDate completeDate;

    // 대여중 = 001 , 반납완료 = 002 , 연체중 = 003
    private String state;
}
