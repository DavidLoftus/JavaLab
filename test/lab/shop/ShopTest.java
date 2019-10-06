package lab.shop;

import org.junit.jupiter.api.Test;

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
}