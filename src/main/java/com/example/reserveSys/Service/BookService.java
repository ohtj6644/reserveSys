package com.example.reserveSys.Service;

import com.example.reserveSys.Entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final SpringAiService springAiService;

    public List<Book> findByBookNo (String bookNo){

        List<Book> book = bookRepository.findByBookNo(bookNo);
        return book;
    }

    public void save(String bookNo,String bookName,String bookWriter){
        Book book = new Book();
        book.setBookNo(bookNo);
        book.setBookName(bookName);
        book.setWriter(bookWriter);
        book.setCreateDate(LocalDate.now());
        book.setBookState("대여가능");
        String aiText ;
        try {
            aiText = springAiService.aiExplanationBack(bookName,bookWriter);
            book.setAiExplanation(aiText);
        }catch (Exception e){
            System.out.println("AI 데이터 생성오류");
        }
        bookRepository.save(book);
    }
}
