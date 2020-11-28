package com.example.usersplit.demo.Models;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("UserExpenseClass in running", HttpStatus.OK);
    }
}