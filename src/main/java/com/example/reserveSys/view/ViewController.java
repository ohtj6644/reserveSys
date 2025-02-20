package com.example.reserveSys.view;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String library001(){
        return "library_001";
    }

    @GetMapping("/library002")
    public String library002(){
        return "library_002";
    }
}
