package lab.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindowShopperTest {

    @Test
    void testGetShoppingList() {
        Customer windowShopper = new WindowShopper();
        assertTrue(windowShopper.getShoppingList().isEmpty());
    }

    @Test
    void testCanPay() {
        Customer windowShopper = new WindowShopper();

        assertFalse(windowShopper.canPay(10.0));
        assertFalse(windowShopper.canPay(0.01));
        assertFalse(windowShopper.canPay(0.0));
    }
}