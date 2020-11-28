package com.example.usersplit.demo;

import com.example.usersplit.demo.Models.RunTest;
import com.example.usersplit.demo.Models.ShowExpenses;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserExpenseManagementApplicationTests {

    @Autowired
    public  ShowExpenses showExpenses;

    @Autowired
    public  RunTest runTest;

    @Test
    void contextLoads() {
        runTest.intialiseUsers();
    }

}
