package com.example.usersplit.demo.Models;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ShowExpenses {

    @Autowired
     UserStore userStore;

    public void showAll(){
        Boolean flag=false;
        Map<String,User> userMap=userStore.getUserData();
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            Map<String,Double> amountBalance=entry.getValue().getAmountBalance();
            if(amountBalance.size()>0) {
                for (Map.Entry<String, Double> userData : amountBalance.entrySet()) {
                    if (userData.getValue() > 0) {
                        System.out.println(entry.getValue().getUserId()+" Owes "+userData.getKey()+" : "+userData.getValue());
                        flag = true;
                    }
                }
            }
        }
        if(!flag){
            System.out.println("No balances");
        }

    }
    public void show(String userId){
        User user=userStore.getUserData().get(userId);
        Map<String,Double> amountBalance=user.getAmountBalance();
        if(amountBalance.size()==0){
            System.out.println("No balances");
        }
        for(Map.Entry<String, Double> userData : amountBalance.entrySet()){
            if(userData.getValue()<0){
                System.out.println(userData.getKey()+" Owes "+userId+" : "+Math.abs(userData.getValue()));
            }
            else if(userData.getValue()>0){
                System.out.println(userId+" Owes "+userData.getKey()+" : "+Math.abs(userData.getValue()));
            }
        }
    }

}

