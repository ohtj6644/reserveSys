package com.example.reserveSys.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //책 번호
    @Column(unique = true)
    private String bookNo;

    //도서 이름
    private String bookName;

    // 글쓴이
    private String writer;

    //생성일자
    @Column(name = "createDate")
    private LocalDate createDate;

    //도서 상태 대여가능 , 대여불가 , 대여중
    private String bookState;

    //Ai 설명
    private String aiExplanation;



}
