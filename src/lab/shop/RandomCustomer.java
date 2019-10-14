package lab.shop;

import java.util.ArrayList;
import java.util.List;

public class RandomCustomer implements Customer {
    private List<Product> shoppingList;
    private double money;
    private List<Receipt> receipts;

    @Deprecated
    public RandomCustomer() {
        shoppingList = new ArrayList<Product>();
        receipts = new ArrayList<Receipt>();

        int noProducts;
        for (Product.Type t : Product.Type.values()) {
            //generate a random int between 0 and 2 (inclusive)
            noProducts = (int) Math.floor(Math.random() * 3);

            if (noProducts > 0) {
                shoppingList.add(new Product(t, noProducts));
            }
        }

        if (shoppingList.isEmpty()) {
            shoppingList.add(new Product(Product.Type.Milk, 1));
        }

        //generate a random number between 15 and 30 (inclusive)
        money = Math.round(Math.random() * 15) + 15;
    }

    public RandomCustomer(List<Product> shoppingList, double money) {
        this.shoppingList = shoppingList;
        this.money = money;
        this.receipts = new ArrayList<Receipt>();
    }

    private double calculateCost() {
        double cost = 0.0;
        for (Product p : shoppingList) {
            cost += p.getCost();
        }
        return cost;
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
    public List<Product> getShoppingList() {
        return shoppingList;
    }

    @Override
    public double getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return shoppingList.toString();
    }

    public void giveReceipt(Receipt r) {
        receipts.add(r);
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }
}

class CustomerException extends RuntimeException {
    public CustomerException(String s) {
        super(s);
    }
}