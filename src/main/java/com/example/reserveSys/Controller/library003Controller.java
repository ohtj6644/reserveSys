package com.example.reserveSys.Controller;

import com.example.reserveSys.Entity.Book;
import com.example.reserveSys.Service.BookService;
import com.example.reserveSys.Service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class library003Controller {

    private final BookService bookService;
    private final RentService rentService;

    @PostMapping("/rent/create")
    public ResponseEntity<String> createBook(@RequestBody Map<String, String> requestBody) {


        String bookNo = requestBody.get("bookNo");
        String rentUser = requestBody.get("rentUser");
        String phoneNo = requestBody.get("phoneNo");


        // 폼 데이터 처리
        System.out.println("도서번호: " + bookNo);
        System.out.println("대여자 성명: " + rentUser);
        System.out.println("대여자 연락처: " + phoneNo);
        System.out.println("==========도서대여==========");

        List<Book> temp = this.bookService.findByBookNo(bookNo);
        if (temp.get(0).getBookState().equals("대여가능")) {
            this.rentService.rentCreate(bookNo,rentUser,phoneNo,temp.get(0).getBookName());
            this.bookService.rentBook(bookNo);
            return ResponseEntity.ok().body("{\"success\": true}"); // 성공 응답

        }else  {
            System.out.println("현재 대여 불가능한 상태 입니다.. ");
            return ResponseEntity
                    .badRequest()  // 400 Bad Request 상태 코드
                    .body("{\"success\": false, \"message\": \"대여 불가능한 상태 입니다.\"}");
        }



    }



}
