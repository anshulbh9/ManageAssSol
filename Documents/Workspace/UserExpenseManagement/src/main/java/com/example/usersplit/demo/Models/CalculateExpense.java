package com.example.usersplit.demo.Models;

import org.omg.CORBA.DynAnyPackage.InvalidValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.InvalidAttributeValueException;
import java.io.InvalidObjectException;
import java.util.Map;
import java.util.Set;

@Component
public class CalculateExpense {

    @Autowired
    private UserStore userStore;

    public void calculateExpensesEqual(String userid,String amount, Set<String> usersOwes){

        User userWhoPaid=userStore.getUserData().get(userid);
        Integer groupSize = usersOwes.size()+1;
        Double amountSplit=0.0d;
        Map<String,Double> userWhoPaidAmountBalance=userWhoPaid.getAmountBalance();
        if(amount!=null) {
             amountSplit = Double.parseDouble(amount) / groupSize;
        }
        for(String userWhoOwed:usersOwes){
            User userOwed=userStore.getUserData().get(userWhoOwed);
            if(userOwed==null){
                throw new NullPointerException("No User exist");
            }
            Map<String, Double> amountBalance=userOwed.getAmountBalance();
            if(amountBalance!=null&&userWhoPaidAmountBalance.containsKey(userWhoOwed)){
                Double bal=userWhoPaid.getAmountBalance().get(userWhoOwed);
                userWhoPaidAmountBalance.put(userWhoOwed,bal-amountSplit);
                amountBalance.put(userid,amountSplit-bal);

            }
            else if(amountBalance!=null){
                userWhoPaidAmountBalance.put(userWhoOwed,-amountSplit);
                amountBalance.put(userid,amountSplit);
            }
            userOwed.setAmountBalance(amountBalance);
            userStore.getUserData().put(userWhoOwed,userOwed);
        }
        userWhoPaid.setAmountBalance(userWhoPaidAmountBalance);
        userStore.getUserData().put(userid,userWhoPaid);

    }

    public void calculateExpensesExact(String userid,String amount,Map<String,Double> amountOwed) throws InvalidAttributeValueException {
        Double finalAmount=0.0d;
        User userWhoPaid=userStore.getUserData().get(userid);
        Map<String,Double> amountBalUserWhoPaid=userWhoPaid.getAmountBalance();
        for(Map.Entry<String, Double> userData : amountOwed.entrySet()){
            User userOwed=userStore.getUserData().get(userData.getKey());
            finalAmount+=userData.getValue();
            Map<String, Double> amountBalance=userOwed.getAmountBalance();
          if(amountBalUserWhoPaid.containsKey(userData.getKey())){
              Double bal=amountBalUserWhoPaid.get(userData.getKey());
              amountBalUserWhoPaid.put(userData.getKey(),bal-userData.getValue());
              amountBalance.put(userid,amountBalance.get(userid)+userData.getValue());

          }
          else{
              amountBalUserWhoPaid.put(userData.getKey(),-userData.getValue());
              amountBalance.put(userid,userData.getValue());
          }
            userOwed.setAmountBalance(amountBalance);
            userStore.getUserData().put(userOwed.getUserId(),userOwed);
        }
        if(!finalAmount.equals(Double.parseDouble(amount))){
            throw new InvalidAttributeValueException("Data is not Correct");
        }
        userWhoPaid.setAmountBalance(amountBalUserWhoPaid);
        userStore.getUserData().put(userid,userWhoPaid);
    }
}
