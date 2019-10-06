package lab.shop;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<Product> shoppingList;
    private double money;

    @Deprecated
    public Customer() {
        shoppingList = new ArrayList<Product>();

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

    public Customer(List<Product> shoppingList, double money) {
        this.shoppingList = shoppingList;
        this.money = money;
    }

    private double calculateCost() {
        double cost = 0.0;
        for (Product p : shoppingList) {
            cost += p.getCost();
        }
        return cost;
    }

    public boolean canPay(double amount) {
        return amount <= money;
    }

    public double pay(double amount) {
        if (!canPay(amount)) {
            throw new CustomerException("Customer does not have enough money.");
        }

        money -= amount;

        return money;
    }

    public List<Product> getShoppingList() {
        return shoppingList;
    }

    @Override
    public String toString() {
        return shoppingList.toString();
    }

}

class CustomerException extends RuntimeException {
    public CustomerException(String s) {
        super(s);
    }
}