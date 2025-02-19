package com.example.reserveSys.Entity.User;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Data
@Getter
@Setter
public class AddUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    public AddUser(String username, String password, List<GrantedAuthority> authorities) {
        this.username=username;
        this.password=password;
    }
}
