package com.dkatalis.free;

public enum ParkingLotCharge {

    instance;

    public int calculate(int hours) {
        if (hours <= 0) {
            return 0;
        }
        int charge = 10;
        if (hours > 2) {
            charge += 10 * (hours - 2);
        }
        return charge;
    }
}
