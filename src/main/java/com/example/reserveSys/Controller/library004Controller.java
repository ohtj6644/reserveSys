package com.example.reserveSys.Controller;

import com.example.reserveSys.Entity.Book;
import com.example.reserveSys.Entity.Rent;
import com.example.reserveSys.Service.BookService;
import com.example.reserveSys.Service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class library004Controller {
    private final BookService bookService;
    private final RentService rentService;



    @PostMapping("/rent/complete/{rentId}")
    public ResponseEntity<String> getBookDetail(@PathVariable("rentId") String rentId) {
        // 도서 상세 정보 조회
        try {
            Rent rent = this.rentService.findById(rentId);
            this.rentService.completeRent(rent);
            System.out.println("반납 성공" + rent.getBookName());
            return ResponseEntity.ok().body("{\"success\": true}"); // 성공 응답
        }catch (Exception e){
            System.out.println("반납실패 ::" + e);
            return ResponseEntity
                    .badRequest()  // 400 Bad Request 상태 코드
                    .body("{\"success\": false, \"message\": \"반납 실패\"}");

        }

    }
}
