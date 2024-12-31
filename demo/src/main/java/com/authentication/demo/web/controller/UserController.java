package com.authentication.demo.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.demo.service.UserServiceImpl;
import com.authentication.demo.web.dto.UserRegistrationRequest;

import lombok.RequiredArgsConstructor;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody UserRegistrationRequest user){
        userService.save(user);
        return ResponseEntity.ok("User registered. Confirmation code sent to email.");
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmCode(@RequestParam String email, @RequestParam String code){

        boolean confirmed = userService.confirmCode(email, code);
        if(confirmed){
            return ResponseEntity.ok("User confirmed successfully");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalided code.");
        }
    }
    
}
