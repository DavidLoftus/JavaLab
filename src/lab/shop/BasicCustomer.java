package lab.shop;

import java.util.List;

public class BasicCustomer implements Customer {

    private List<Product> shoppingList;
    private double money;

    public BasicCustomer(List<Product> shoppingList, double money) {
        this.shoppingList = shoppingList;
        this.money = money;
    }

    @Override
    public List<Product> getShoppingList() {
        return shoppingList;
    }

    @Override
    public void setShoppingList(List<Product> shoppingList) {
        this.shoppingList = shoppingList;
    }

    @Override
    public boolean canPay(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount can't be negative");
        }
        return amount <= money;
    }

    @Override
    public double pay(double amount) {
        if (!canPay(amount)) {
            throw new CustomerException("Customer does not have enough money.");
        }

        money -= amount;

        return money;
    }

    @Override
    public double getMoney() {
        return money;
    }
}
