package com.example.reserveSys.Service;


import com.example.reserveSys.Entity.Book;
import com.example.reserveSys.Entity.Rent;
import com.example.reserveSys.Repository.RentRepository;
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
public class RentService {
    private final RentRepository rentRepository;

    private final BookService bookService;

    public void rentCreate(String bookNo,String rentUser,String phoneNo,String bookName){
        Rent rent = new Rent();
        rent.setRentDate(LocalDate.now());
        rent.setExpiryDate(rent.getRentDate().plusDays(7)); //대여일 7일로 설정
        rent.setBookNo(bookNo);
        rent.setRentUserName(rentUser);
        rent.setPhoneNo(Integer.valueOf(phoneNo));
        rent.setBookName(bookName);
        rent.setState("001");
        this.rentRepository.save(rent);

    }

    public Page<Rent> getPage(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("rentDate"));
        Pageable pageable = PageRequest.of(page,15,Sort.by(sorts));
        return this.rentRepository.findAll(pageable);
    }

    public Page<Rent> getRentMainPage(){
        int page = 0;
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("rentDate"));
        Pageable pageable = PageRequest.of(page,8,Sort.by(sorts));
        return this.rentRepository.findAll(pageable);
    }
    public Page<Rent> getSearchPage(int page, String bookName) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("rentDate"));
        Pageable pageable = PageRequest.of(page, 15, Sort.by(sorts));

        // bookName에 대한 조건 추가
        Specification<Rent> spec = (root, query, builder) -> {
            if (bookName != null && !bookName.isEmpty()) {
                return builder.like(root.get("bookName"), "%" + bookName + "%");
            }
            return builder.conjunction(); // 조건이 없으면 전체 검색
        };

        return this.rentRepository.findAll(spec , pageable);
    }


    public Rent findById(String id){
        int rentId = Integer.parseInt(id);
        return this.rentRepository.findById(rentId);
    }

    public void completeRent(Rent rent){
    rent.setState("002");
    rent.setCompleteDate(LocalDate.now());
    this.rentRepository.save(rent);
    this.bookService.completeRent(rent.getBookNo());
    }

    public int getRentCount(){
        int rentCount = 0;
        List<Rent> temp = this.rentRepository.findAll();
        rentCount = temp.size();
        return rentCount;
    }
    public int getExpiryCount(){
        int expiryCount =0;
        String state = "003";
        List<Rent> temp = this.rentRepository.findByState(state);
        expiryCount = temp.size();
        return expiryCount;
    }
}
