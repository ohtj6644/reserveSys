package com.example.reserveSys.view;


import com.example.reserveSys.Entity.Book;
import com.example.reserveSys.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
