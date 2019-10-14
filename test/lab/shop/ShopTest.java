package lab.shop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    @Test
    void testConstructors() {
        ShopAssistant sa = new ShopAssistant(1, "John");
        CashRegister cashRegister = new CashRegister(0.0);

        Shop shop1 = new Shop(10, cashRegister, sa);
        assertEquals(cashRegister, shop1.getCashRegister());
        assertEquals(shop1, sa.getShop());

        Shop shop2 = new Shop(10, 2.0, sa);
        assertEquals(2.0, shop2.getCashRegister().getMoney());
        assertEquals(shop2, sa.getShop());
    }

    @Test
    void testHasProduct() {
        ShopAssistant sa = new ShopAssistant(1, "John");
        Shop shop = new Shop(10, 0.0, sa);

        for (Product.Type type : Product.Type.values()) {
            assertTrue(shop.hasProduct(new Product(type, 10)));
            assertTrue(shop.hasProduct(new Product(type, 5)));
            assertTrue(shop.hasProduct(new Product(type, 0)));
            assertFalse(shop.hasProduct(new Product(type, 11)));
        }
    }

    @Test
    void testSoldProduct() {
        ShopAssistant sa = new ShopAssistant(1, "John");
        Shop shop = new Shop(10, 0.0, sa);

        shop.soldProduct(new Product(Product.Type.Bacon, 5));

        for (Product.Type type : Product.Type.values()) {
            if (type == Product.Type.Bacon) {
                assertFalse(shop.hasProduct(new Product(type, 10)));
                assertFalse(shop.hasProduct(new Product(type, 6)));
                assertTrue(shop.hasProduct(new Product(type, 5)));
                assertTrue(shop.hasProduct(new Product(type, 0)));
            } else {
                assertFalse(shop.hasProduct(new Product(type, 11)));
                assertTrue(shop.hasProduct(new Product(type, 10)));
                assertTrue(shop.hasProduct(new Product(type, 5)));
                assertTrue(shop.hasProduct(new Product(type, 0)));
            }
        }

        shop.soldProduct(new Product(Product.Type.Bacon, 5));

        for (Product.Type type : Product.Type.values()) {
            if (type == Product.Type.Bacon) {
                assertFalse(shop.hasProduct(new Product(type, 1)));
                assertTrue(shop.hasProduct(new Product(type, 0)));
            } else {
                assertFalse(shop.hasProduct(new Product(type, 11)));
                assertTrue(shop.hasProduct(new Product(type, 10)));
                assertTrue(shop.hasProduct(new Product(type, 5)));
                assertTrue(shop.hasProduct(new Product(type, 0)));
            }
        }

        assertThrows(IllegalArgumentException.class, () -> {
            shop.soldProduct(new Product(Product.Type.Bacon, 5));
        });
    }

    @Test
    void testWalkIn() {
        ShopAssistant sa = new ShopAssistant(1, "John");
        CashRegister cashRegister = new CashRegister(0.0);
        Shop shop = new Shop(10, cashRegister, sa);

        List<Product> shoppingList = new ArrayList<Product>();
        shoppingList.add(new Product(Product.Type.Bacon, 2));
        shoppingList.add(new Product(Product.Type.Milk, 3));
        double cost = Product.Type.Bacon.cost() * 2 + Product.Type.Milk.cost() * 3;

        Customer customer = new RandomCustomer(shoppingList, 20.0);
        shop.walkIn(customer);

        assertEquals(20.0 - cost, customer.getMoney());
        assertEquals(cost, cashRegister.getMoney());

        assertEquals(8, shop.getBacon().getQuantity());
        assertEquals(7, shop.getMilk().getQuantity());
        assertEquals(10, shop.getBiscuits().getQuantity());
    }

    @Test
    void testWalkInWithoutStock() {
        ShopAssistant sa = new ShopAssistant(1, "John");
        CashRegister cashRegister = new CashRegister(0.0);
        // Shop has no stock, all purchases will silently fail
        Shop shop = new Shop(0, cashRegister, sa);

        List<Product> shoppingList = new ArrayList<Product>();
        shoppingList.add(new Product(Product.Type.Bacon, 2));
        shoppingList.add(new Product(Product.Type.Milk, 3));
        double cost = Product.Type.Bacon.cost() * 2 + Product.Type.Milk.cost() * 3;

        Customer customer = new RandomCustomer(shoppingList, 20.0);
        shop.walkIn(customer);

        assertEquals(20.0, customer.getMoney());
        assertEquals(0.0, cashRegister.getMoney());

        assertEquals(0, shop.getBacon().getQuantity());
        assertEquals(0, shop.getMilk().getQuantity());
        assertEquals(0, shop.getBiscuits().getQuantity());
    }

    @Test
    void testWalkInWithoutMoney() {
        ShopAssistant sa = new ShopAssistant(1, "John");
        CashRegister cashRegister = new CashRegister(0.0);
        Shop shop = new Shop(10, cashRegister, sa);

        List<Product> shoppingList = new ArrayList<Product>();
        shoppingList.add(new Product(Product.Type.Bacon, 2));
        shoppingList.add(new Product(Product.Type.Milk, 3));
        double cost = Product.Type.Bacon.cost() * 2 + Product.Type.Milk.cost() * 3;

        // Customer can't afford shopping list
        Customer customer = new RandomCustomer(shoppingList, 0.0);

        assertThrows(ShopException.class, () -> {
            shop.walkIn(customer);
        });
    }
}