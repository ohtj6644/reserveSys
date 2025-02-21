package com.example.reserveSys.Controller;


import com.example.reserveSys.Entity.Book;
import com.example.reserveSys.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class library002Controller {

    private final BookService bookService;

    @PostMapping("/book/create")
    public ResponseEntity<String> createBook(@RequestBody Map<String, String> requestBody) {


        String bookNo = requestBody.get("bookNo");
        String bookName = requestBody.get("bookName");
        String bookWriter = requestBody.get("bookWriter");
        // 폼 데이터 처리
        System.out.println("도서번호: " + bookNo);
        System.out.println("제목: " + bookName);
        System.out.println("글쓴이: " + bookWriter);
        System.out.println("==========도서등록==========");

        List<Book> temp = this.bookService.findByBookNo(bookNo);
        if (temp.size()>0) {
            System.out.println("이미 존재하는 도서 입니다. ");
            return ResponseEntity
                    .badRequest()  // 400 Bad Request 상태 코드
                    .body("{\"success\": false, \"message\": \"해당 도서번호는 이미 존재합니다.\"}");
        }else if (temp.size()==0) {
            this.bookService.save(bookNo,bookName,bookWriter);
        }


        // 데이터 저장 로직 (예: DB에 저장)

        return ResponseEntity.ok().body("{\"success\": true}"); // 성공 응답
    }

}
