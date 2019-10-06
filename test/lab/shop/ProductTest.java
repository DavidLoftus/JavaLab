package lab.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testNegativeQuantity() {
        try {
            Product product = new Product(Product.Type.Bacon, -2);
            fail("expected exception with negative quantity");
        } catch (IllegalArgumentException e) {
            // We expect an en exception here
        }
    }

    @Test
    void testCoffee() {
        Product coffee = new Product(Product.Type.Coffee, 2);

        assertEquals(coffee.getCost(), 9.98, 0.001);
    }

    @Test
    void testBiscuits() {
        Product coffee = new Product(Product.Type.Biscuits, 2);

        assertEquals(coffee.getCost(), 5.98, 0.001);
    }

    @Test
    void testBacon() {
        Product coffee = new Product(Product.Type.Bacon, 2);

        assertEquals(coffee.getCost(), 7.58, 0.001);
    }

    @Test
    void testMilk() {
        Product coffee = new Product(Product.Type.Milk, 2);

        assertEquals(coffee.getCost(), 4.0, 0.001);
    }

    @Test
    void testNewspaper() {
        Product coffee = new Product(Product.Type.Newspaper, 2);

        assertEquals(coffee.getCost(), 2.0, 0.001);
    }

}