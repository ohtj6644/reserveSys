package com.example.reserveSys.Service;

import com.example.reserveSys.Entity.Book;
import com.example.reserveSys.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Page<Book> getPage(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("bookNo"));
        Pageable pageable = PageRequest.of(page,15,Sort.by(sorts));
        return this.bookRepository.findAll(pageable);
    }
    public Page<Book> getBookMainPage(){
        int page = 0;
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("bookNo"));
        Pageable pageable = PageRequest.of(page,8,Sort.by(sorts));
        return this.bookRepository.findAll(pageable);
    }
    public Page<Book> getSearchPage(int page, String bookName) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 15, Sort.by(sorts));

        // bookName에 대한 조건 추가
        Specification<Book> spec = (root, query, builder) -> {
            if (bookName != null && !bookName.isEmpty()) {
                return builder.like(root.get("bookName"), "%" + bookName + "%");
            }
            return builder.conjunction(); // 조건이 없으면 전체 검색
        };

        return this.bookRepository.findAll(spec, pageable);
    }

    public Book getBookByBookNo(String bookNo) throws Exception{
        List<Book> temp = this.bookRepository.findByBookNo(bookNo);
        if(temp.size()==1){
            return temp.get(0);
        }else {
            Exception exception = new Exception();
            throw exception;
        }

    }

    public void rentBook(String bookNo){
        List<Book> temp = this.bookRepository.findByBookNo(bookNo);
        Book book = temp.get(0);
        book.setBookState("대여중");
        this.bookRepository.save(book);
    }

    public void completeRent(String bookNo){
        List<Book> temp = this.bookRepository.findByBookNo(bookNo);
        Book book = temp.get(0);
        book.setBookState("대여가능");
        this.bookRepository.save(book);
    }

    public int getBookCount(){
        int bookCount = 0;
        List<Book> temp = this.bookRepository.findAll();
        bookCount = temp.size();
        return bookCount;
    }
}
