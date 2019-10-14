package lab.shop;

import java.util.List;

public interface Customer {
    List<Product> getShoppingList();
    void setShoppingList(List<Product> shoppingList);

    boolean canPay(double totalCost);

    double pay(double totalCost);

    double getMoney();
}
