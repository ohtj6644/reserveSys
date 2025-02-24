package com.example.reserveSys.Controller.view;


import com.example.reserveSys.Entity.Book;
import com.example.reserveSys.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final BookService bookService;

    @GetMapping("/")
    public String library001(){
        return "library_001";
    }

    @GetMapping("/library002")
    public String library002(){
        return "library_002";
    }

    @GetMapping("/library003")
    public String library003(Model model,@RequestParam(value = "page",defaultValue = "0")int page){
        Page<Book> bookPage = this.bookService.getPage(page);

        model.addAttribute("paging",bookPage);

        return "library_003";
    }

    @PostMapping("/book/search/{bookName}")
    public String searchBook(@PathVariable("bookName") String bookName, Model model, @RequestParam(value = "page",defaultValue = "0")int page) {

        // 폼 데이터 처리
        System.out.println("제목: " + bookName);
        System.out.println("==========도서검색==========");

        Page<Book> bookPage = this.bookService.getSearchPage(page,bookName);

        model.addAttribute("paging",bookPage);
        return "library_003 :: bookList";
    }

    //모달 뷰어
    @GetMapping("/book/detail/{bookNo}")
    public String getBookDetail(@PathVariable("bookNo") String bookNo, Model model) {
        // 도서 상세 정보 조회
        Book book = new Book();
        try {
            book = bookService.getBookByBookNo(bookNo);
        }catch (Exception e){
            System.out.println("도서 상세 오류 "+ e);
        }

        model.addAttribute("book", book);
        return "library_003_m01 :: bookModal";  // 상세 정보를 담은 HTML 반환
    }
}
