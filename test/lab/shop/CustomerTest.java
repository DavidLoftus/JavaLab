package lab.shop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testCustomer() {
        List<Product> shoppingList = new ArrayList<Product>();
        shoppingList.add(new Product(Product.Type.Bacon, 2));
        shoppingList.add(new Product(Product.Type.Milk, 3));

        Customer customer = new Customer(shoppingList, 10.0);


    }

}