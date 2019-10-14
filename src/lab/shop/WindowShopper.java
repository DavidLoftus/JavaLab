package lab.shop;

import java.util.ArrayList;
import java.util.List;

public class WindowShopper implements Customer {
    @Override
    public List<Product> getShoppingList() {
        return new ArrayList<Product>();
    }

    @Override
    public void setShoppingList(List<Product> shoppingList) {
        throw new UnsupportedOperationException("Can't set shoppingList for window shopper.");
    }

    @Override
    public boolean canPay(double totalCost) {
        return false;
    }

    @Override
    public double pay(double totalCost) {
        throw new UnsupportedOperationException("Window shopper can't pay.");
    }

    @Override
    public double getMoney() {
        return 0;
    }
}
