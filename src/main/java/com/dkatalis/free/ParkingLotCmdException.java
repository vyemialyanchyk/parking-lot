package com.dkatalis.free;

public class ParkingLotCmdException extends RuntimeException {
    public ParkingLotCmdException(String message) {
        super(message);
    }

    public ParkingLotCmdException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParkingLotCmdException(Throwable cause) {
        super(cause);
    }
}
