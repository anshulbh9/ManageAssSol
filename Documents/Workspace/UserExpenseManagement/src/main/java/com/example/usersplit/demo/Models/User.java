package com.example.usersplit.demo.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
public class User{

    private String userId;

    private String emailId;

    private String mobileNo;

    private Map<String,Double> amountBalance;

}