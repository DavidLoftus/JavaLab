package lab.shop;

import java.util.List;

public interface Customer {
    List<Product> getShoppingList();

    boolean canPay(double totalCost);

    double pay(double totalCost);

    void giveReceipt(Receipt receipt);

    double getMoney();
}
