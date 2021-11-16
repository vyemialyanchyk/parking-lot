package com.dkatalis.free;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 *
 */
public class AppTest {
    @Test
    public void verifyChargeCalculation() {
        assertTrue(ParkingLotCharge.instance.calculate(-1) == 0);
        assertTrue(ParkingLotCharge.instance.calculate(0) == 0);
        assertTrue(ParkingLotCharge.instance.calculate(1) == 10);
        assertTrue(ParkingLotCharge.instance.calculate(2) == 10);
        assertTrue(ParkingLotCharge.instance.calculate(3) == 20);
        assertTrue(ParkingLotCharge.instance.calculate(4) == 30);
        assertTrue(ParkingLotCharge.instance.calculate(5) == 40);
        final int hoursIn20Years = 20 * 366 * 24;
        for (int i = 6; i < hoursIn20Years; i++) {
            int expectedCharge = 10 + 10 * (i - 2);
            assertTrue(ParkingLotCharge.instance.calculate(i) == expectedCharge);
        }
    }
}
