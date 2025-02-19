package com.example.reserveSys.Entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AddUser, Long> {
    Optional<AddUser> findByUsername(String username);


    Optional<AddUser> findByusername(String username);
}
