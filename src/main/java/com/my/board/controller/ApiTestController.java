package com.my.board.controller;

import com.my.board.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ApiTestController {

    @GetMapping("/api")
    public String apiMain() {
        return "test/api";
    }

    @PostMapping("/apiTest")
    //json으로 바꿔서 웹 바디에서 직접받음
    @ResponseBody
    public LoginDto GetTest() {

        return new LoginDto("jang", "1111");
    }

    @GetMapping("/stringTest")
    @ResponseBody
    public String postString() {
        return "스트링데이터 보냄";
    }

    @PostMapping("apiEntityTest")
    //@RequestBody에서 직접받으려면 Json으로 전달해야 함
    public ResponseEntity<String> apiEntityTest(@RequestBody LoginDto dto) {
        String info = dto.getUserId() + "/" + dto.getPassword();
        System.out.println(info);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(info);
    }

    @PostMapping("/mapTest")
    @ResponseBody
    public Map<String, String> apiMapTest(@RequestBody LoginDto dto) {
        Map<String, String> map = new HashMap<>();
        map.put("아이디", dto.getUserId());
        map.put("비밀번호", dto.getPassword());
        return map;

    }
    @DeleteMapping("delete")
    @ResponseBody
    public String delete(){
        return "삭제되었습니다.";
    }
}
