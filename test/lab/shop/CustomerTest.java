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

        Customer customer = new Customer(shoppingList, 10.0);

        assertEquals(shoppingList, customer.getShoppingList());
    }

    @Test
    void testCustomerCanPay() {
        List<Product> shoppingList = new ArrayList<Product>();
        shoppingList.add(new Product(Product.Type.Bacon, 2));
        shoppingList.add(new Product(Product.Type.Milk, 3));

        Customer customer = new Customer(shoppingList, 10.0);

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

        Customer customer = new Customer(shoppingList, 10.0);

        assertTrue(customer.canPay(10.0));

        customer.pay(5.5);

        assertTrue(customer.canPay(4.5));
        assertFalse(customer.canPay(10.0));

        customer.pay(4.5);

        assertTrue(customer.canPay(0.0));
        assertFalse(customer.canPay(4.5));

        assertThrows(CustomerException.class, () -> {
            customer.pay(4.5);
        });
    }

}