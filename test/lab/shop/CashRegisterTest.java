package lab.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {
    @Test
    void testNegativeMoney() {
        try {
            CashRegister register = new CashRegister(-1.0);
            fail("expected exception to be thrown on negative money");
        } catch (CashRegisterException e) {
            // We expect this exception to be thrown
        }

        CashRegister register = new CashRegister(0.0);

        try {
            register.setMoney(-1.0);
            fail("expected exception to be thrown on negative money");
        } catch (CashRegisterException e) {
            // We expect this exception to be thrown
        }

        try {
            register.add(-1.0);
            fail("expected exception to be thrown on negative money");
        } catch (CashRegisterException e) {
            // We expect this exception to be thrown
        }

        try {
            register.remove(1.0);
            fail("expected exception to be thrown on removing too much money");
        } catch (CashRegisterException e) {
            // We expect this exception to be thrown
        }

        try {
            register.add(-1.0);
            fail("expected exception to be thrown on removing negative money");
        } catch (CashRegisterException e) {
            // We expect this exception to be thrown
        }
    }

    @Test
    void testRegister() {

        CashRegister register = new CashRegister(0);
        assertEquals(0.0, register.getMoney(), 0.001);

        register.add(5.0);
        assertEquals(5.0, register.getMoney(), 0.001);

        register.remove(2.0);
        assertEquals(3.0, register.getMoney(), 0.001);

        register.setMoney(2.5);
        assertEquals(2.5, register.getMoney(), 0.001);
    }
}