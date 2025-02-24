package com.example.reserveSys.Service;


import com.example.reserveSys.Entity.Book;
import com.example.reserveSys.Entity.Rent;
import com.example.reserveSys.Repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {
    private final RentRepository rentRepository;

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
}
