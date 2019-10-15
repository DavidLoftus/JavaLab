package lab.shop;

import java.util.ArrayList;
import java.util.List;

public class SpontaneousShopper implements Customer {
    private List<Product> shoppingList;
    private double money;

    public SpontaneousShopper() {
        shoppingList = null;
        //generate a random number between 15 and 30 (inclusive)
        money = Math.round(Math.random() * 15) + 15;
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
        if (shoppingList == null) {
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
        }
        return shoppingList;
    }

    @Override
    public void setShoppingList(List<Product> shoppingList) {
        this.shoppingList = shoppingList;
    }

    @Override
    public double getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "SpontaneousShopper " + getShoppingList().toString();
    }
}
