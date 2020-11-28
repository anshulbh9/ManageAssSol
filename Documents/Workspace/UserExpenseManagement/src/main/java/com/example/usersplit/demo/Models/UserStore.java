package com.example.usersplit.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
public class UserStore {

    public Map<String,User> userData=new HashMap<>();
}
