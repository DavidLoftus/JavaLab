package lab.shop;

import java.util.ArrayList;
import java.util.List;

public class CashRegister {

    //instance variable money
    private double money;

    //instance variable transactions
    private List<String> transactions;

    //constructor
    public CashRegister(double initialAmount) {
        money = initialAmount;
        validateMoney();

        //construct transactions
        transactions = new ArrayList<String>();
    }

    //method to check money is never < 0
    private void validateMoney() {
        if (money < 0) {
            /*
             * I've included here an example of how you could define your
             * own Exception. However the same code, using
             * IllegalArgumentException (in all cases where I use
             * CashRegisterException) instead would answer the question
             * as specified.
             */
            throw new CashRegisterException("Money cannot fall below 0");
        }
    }

    //money accessor
    public double getMoney() {
        return money;
    }

    //money mutator + check for valid amount
    public void setMoney(double d) {
        money = d;
        validateMoney();
    }

    //toString method, which reports the amount of money in the cash register
    public String toString() {
        return "Currently " + money + " in the till";
    }

    //method to add an amount of money to the register
    //also checks that the amount added is positive
    public void add(double d) {
        //check d is positive, throw exception otherwise
        if (d > 0) {
            //increase money by d
            money += d;
            //add the transaction to the "memory" of the till
            transactions.add("Added " + d + ". Total now: " + money);
        } else {
            throw new CashRegisterException("Can only add positive amounts to the till. Supplied: " + d);
        }
    }

    // Removes money from cash register
    public void remove(double d) {
        //check d is positive, throw exception otherwise
        if (d > 0) {
            //decrease money by d
            money -= d;
            //check money > 0
            validateMoney();
            //add the transaction to the "memory" of the till
            transactions.add("Removed " + d + ". Total now: " + money);
        } else {
            throw new CashRegisterException("Can only remove positive amounts to the till. Supplied: " + d);
        }
    }

    public void printLastTransactions(int noTransactions) {
        //if insufficient transactions, print all of them
        if (noTransactions < 1) {
            System.out.println("noTransactions needs to be greater than or equal to 1");
        } else if (noTransactions > transactions.size()) {
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        } else {
            //otherwise print the last noTransactions of transactions
            for (int i = transactions.size() - noTransactions; i < transactions.size(); i++) {
                System.out.println(transactions.get(i));
            }
        }
    }
}

class CashRegisterException extends RuntimeException {
    public CashRegisterException(String s) {
        super(s);
    }
}