package lab.shop;

import java.util.ArrayList;
import java.util.List;

public class ShopAssistant {

    private int id;
    private String name;
    private double hoursWorked;
    private double hourlyPay;
    private Shop shop;

    public ShopAssistant(int id, String name) {
        this.id = id;
        this.name = name;

        this.hoursWorked = 0;
        this.hourlyPay = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("can't work negative hours");
        }
        if (hoursWorked > 10) {
            throw new IllegalArgumentException("can't work more than 10 hours");
        }
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyPay() {
        return hourlyPay;
    }

    public void setHourlyPay(double hourlyPay) {
        if (hourlyPay < 0) {
            throw new IllegalArgumentException("can't have a negative wage");
        }
        if (hourlyPay > 30 || hourlyPay < 10) {
            throw new IllegalArgumentException("wage must be between 10 and 30");
        }
        this.hourlyPay = hourlyPay;
    }

    public double calculatePay() {
        return this.hourlyPay * this.hoursWorked;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Receipt serve(Customer customer) {
        Receipt receipt = new Receipt(this.name, this.id);
        for (Product p : customer.getShoppingList()) {
            if (shop.hasProduct(p)) {
                receipt.addProduct(p);
            } else {
                System.out.printf("Product %s is not in stock, try again later.\n", p);
            }
        }

        double totalCost = receipt.calculateTotalCost();

        if (totalCost != 0.0) {
            if (!customer.canPay(totalCost)) {
                throw new ShopException("Sorry you do not have enough money, total: " + totalCost);
            }
            customer.pay(totalCost);
            shop.getCashRegister().add(totalCost);
        }

        for (Product p : receipt.getProducts()) {
            shop.soldProduct(p);
        }
        return receipt;
    }
}
