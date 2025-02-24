package com.example.reserveSys.Repository;


import com.example.reserveSys.Entity.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

    Page<Rent> findAll (Pageable pageable);
}
