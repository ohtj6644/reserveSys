package com.example.reserveSys.Controller;


import com.example.reserveSys.Entity.Book;
import com.example.reserveSys.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class library002Controller {

    private BookService bookService;

    @PostMapping("/book/create")
    public ResponseEntity<?> createBook(@RequestParam("bookNo") String bookNo, @RequestParam("bookName") String bookName, @RequestParam("bookWriter") String bookWriter) {
        // 폼 데이터 처리
        System.out.println("도서번호: " + bookNo);
        System.out.println("제목: " + bookName);
        System.out.println("글쓴이: " + bookWriter);
        System.out.println("==========도서등록==========");

        List<Book> temp = bookService.findByBookNo(bookNo);
        if (temp.size()>0) {
            return ResponseEntity
                    .badRequest()  // 400 Bad Request 상태 코드
                    .body("{\"success\": false, \"message\": \"해당 도서번호는 이미 존재합니다.\"}");
        }else {
            bookService.save(bookNo,bookName,bookWriter);
        }


        // 데이터 저장 로직 (예: DB에 저장)

        return ResponseEntity.ok().body("{\"success\": true}"); // 성공 응답
    }

}
