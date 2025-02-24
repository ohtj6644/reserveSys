package com.example.reserveSys.Repository;


import com.example.reserveSys.Entity.Book;
import com.example.reserveSys.Entity.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

    Page<Rent> findAll(Specification<Rent> spe, Pageable pageable);
    Rent findById(int id);

    List<Rent> findByState(String state);
}
