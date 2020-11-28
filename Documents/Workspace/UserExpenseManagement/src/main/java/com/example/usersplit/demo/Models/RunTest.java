package com.example.usersplit.demo.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InvalidAttributeValueException;
import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class RunTest {

    @Autowired
    private UserStore userStore;

    @Autowired
    private ShowExpenses showExpenses;

    @Autowired
    private CalculateExpense calculateExpense;

    public void intialiseUsers(){

        User user1=new User();
        user1.setUserId("user1");
        user1.setMobileNo("89898989");
        user1.setEmailId("abc@gmail.com");
        user1.setAmountBalance(new HashMap<>());

        User user2=new User();
        user2.setUserId("user2");
        user2.setMobileNo("289898989");
        user2.setEmailId("bcd@gmail.com");
        user2.setAmountBalance(new HashMap<>());

        User user3=new User();
        user3.setUserId("user3");
        user3.setMobileNo("389898989");
        user3.setEmailId("cde@gmail.com");
        user3.setAmountBalance(new HashMap<>());

        User user4=new User();
        user4.setUserId("user4");
        user4.setMobileNo("589898989");
        user4.setEmailId("efg@gmail.com");
        user4.setAmountBalance(new HashMap<>());

        userStore.getUserData().put(user1.getUserId(),user1);
        userStore.getUserData().put(user2.getUserId(),user2);
        userStore.getUserData().put(user3.getUserId(),user3);
        userStore.getUserData().put(user4.getUserId(),user4);

        showExpenses.showAll();
        showExpenses.show("user1");

        Set<String> expenses1=new HashSet<>();
        expenses1.add("user2");
        expenses1.add("user3");
        expenses1.add("user4");
        String userExpense1="user1";
        String userAmount1="1000";
        calculateExpense.calculateExpensesEqual(userExpense1,userAmount1,expenses1);
        showExpenses.showAll();
        Set<String> expenses11=new HashSet<>();
        expenses11.add("user1");
        expenses11.add("user3");
        expenses11.add("user4");
        String userExpense11="user2";
        String userAmount11="1000";
        calculateExpense.calculateExpensesEqual(userExpense11,userAmount11,expenses11);
        showExpenses.showAll();
        //showExpenses.show("user4");
        //showExpenses.show("user1");
        Map<String,Double> expenses2=new HashMap<>();
        expenses2.put("user3",880.0);
        expenses2.put("user2",370.0);
        String userExpense2="user1";
        String userAmount2="1250";
        try {
            calculateExpense.calculateExpensesExact(userExpense2,userAmount2,expenses2);
        }
        catch (InvalidAttributeValueException e){
            System.out.println(e.toString());
        }
        //showExpenses.showAll();
        //showExpenses.show("user1");
        //showExpenses.showAll();
        Set<String> expenses12=new HashSet<>();
        expenses12.add("user1");
        expenses12.add("user3");
        expenses12.add("user4");
        String userExpense12="user2";
        String userAmount12="1000";
        calculateExpense.calculateExpensesEqual(userExpense12,userAmount12,expenses12);
        showExpenses.showAll();
        Map<String,Double> expenses3=new HashMap<>();
        expenses3.put("user1",480.0);
        expenses3.put("user3",240.0);
        String userExpense3="user4";
        String userAmount3="720";
        try {
            calculateExpense.calculateExpensesExact(userExpense3, userAmount3, expenses3);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        showExpenses.show("user1");
        showExpenses.showAll();
        showExpenses.showAll();
    }

}
