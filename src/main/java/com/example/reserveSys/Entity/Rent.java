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
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id")
    private AddUser borrower;


    private int bookId;

    @Column(name = "rentDate")
    private LocalDateTime rentDate;

    // 대여중 = 001 , 반납완료 = 002 , 연체중 = 003
    private int state;
}
