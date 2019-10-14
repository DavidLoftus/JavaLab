package lab.shop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testCustomerShoppingList() {
        List<Product> shoppingList = new ArrayList<Product>();
        shoppingList.add(new Product(Product.Type.Bacon, 2));
        shoppingList.add(new Product(Product.Type.Milk, 3));

        Customer customer = new RandomCustomer(shoppingList, 10.0);

        assertEquals(shoppingList, customer.getShoppingList());
    }

    @Test
    void testCustomerCanPay() {
        List<Product> shoppingList = new ArrayList<Product>();
        shoppingList.add(new Product(Product.Type.Bacon, 2));
        shoppingList.add(new Product(Product.Type.Milk, 3));

        Customer customer = new RandomCustomer(shoppingList, 10.0);

        assertTrue(customer.canPay(10.0));
        assertTrue(customer.canPay(0.0));
        assertTrue(customer.canPay(5.0));

        assertFalse(customer.canPay(10.01));
        assertFalse(customer.canPay(20.0));

        assertThrows(IllegalArgumentException.class, () -> {
           customer.canPay(-1.0);
        });
    }

    @Test
    void testCustomerPay() {
        List<Product> shoppingList = new ArrayList<Product>();
        shoppingList.add(new Product(Product.Type.Bacon, 2));
        shoppingList.add(new Product(Product.Type.Milk, 3));

        Customer customer = new RandomCustomer(shoppingList, 10.0);

        assertTrue(customer.canPay(10.0));

        customer.pay(5.5);
        assertEquals(4.5, customer.getMoney(), 0.001);

        customer.pay(4.5);
        assertEquals(0.0, customer.getMoney(), 0.001);

        assertThrows(CustomerException.class, () -> {
            customer.pay(4.5);
        });
    }

}