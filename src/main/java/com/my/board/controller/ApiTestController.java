package com.my.board.controller;

import com.my.board.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiTestController {
    @PostMapping("/apiTest")
    @ResponseBody
    public LoginDto GetTest(){

        return new LoginDto("jang","1111");
    }
}
