package com.example.reserveSys.Service;


import com.example.reserveSys.Entity.Rent;
import com.example.reserveSys.Repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RentService {
    private final RentRepository rentRepository;

    public void rentCreate(String bookNo,String rentUser,String phoneNo){
        Rent rent = new Rent();
        rent.setRentDate(LocalDate.now());
        rent.setBookNo(bookNo);
        rent.setRentUserName(rentUser);
        rent.setPhoneNo(Integer.valueOf(phoneNo));
        this.rentRepository.save(rent);

    }
}
