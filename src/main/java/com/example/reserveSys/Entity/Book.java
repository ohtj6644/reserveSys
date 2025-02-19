package com.example.reserveSys.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Book {
    @Id
    private String bookId;

    //책 번호
    private String bookNo;

    //도서 이름
    private String bookName;

    // 글쓴이
    private String writer;

    //생성일자
    private LocalDateTime createDate;

    //도서 상태
    private String bookState;




}
