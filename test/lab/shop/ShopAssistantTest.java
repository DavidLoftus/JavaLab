package lab.shop;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShopAssistantTest {
    ShopAssistant sa;

    @BeforeEach
    void setUp() throws Exception {
        sa = new ShopAssistant(1, "Simon");
    }

    @Test
    void testHoursWorkedPositive() {
        sa.setHourlyPay(10);
        sa.setHoursWorked(8);
        assertEquals(sa.calculatePay(), 80);
    }

    @Test
    void testHoursWorkedNegative() {
        try{
            sa.setHourlyPay(-2);
            fail("Should not allow negative Pay");
        } catch(IllegalArgumentException ex) {
            //do nothing
        }

        try{
            sa.setHoursWorked(-2);
            fail("Should not allow negative hours worked");
        } catch(IllegalArgumentException ex) {}

    }

    @Test
    void testInaccurateHours() {
        try {
            sa.setHourlyPay(100);
            fail("Workers cannot earn more than 30 per hour");
        } catch (IllegalArgumentException ignored) {}

        try {
            sa.setHourlyPay(9);
            fail("Minimum Wage is 10 per hour");
        } catch (IllegalArgumentException ignored) {}

        try {
            sa.setHoursWorked(10.5);
            fail("Shop assistants cannot work more than 10 hours per day");
        } catch (IllegalArgumentException ignored) {}
    }
}