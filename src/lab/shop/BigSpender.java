package lab.shop;

import java.util.ArrayList;
import java.util.List;

public class BigSpender extends BasicCustomer {
    private static List<Product> randomShoppingList() {
        List<Product> shoppingList = new ArrayList<Product>();

        int noProducts;
        for (Product.Type t : Product.Type.values()) {
            //generate a random int between 0 and 2 (inclusive)
            noProducts = (int) Math.floor(Math.random() * 10);

            if (noProducts > 0) {
                shoppingList.add(new Product(t, noProducts));
            }
        }

        if (shoppingList.isEmpty()) {
            shoppingList.add(new Product(Product.Type.Milk, 5));
        }
        return shoppingList;
    }

    public BigSpender() {
        super(randomShoppingList(), Math.round(Math.random() * 100) + 15);
    }

    @Override
    public String toString() {
        return "BigShopper " + getShoppingList().toString();
    }
}
