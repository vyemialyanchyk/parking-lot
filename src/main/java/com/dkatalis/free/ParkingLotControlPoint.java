package com.dkatalis.free;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.function.Function;

public class ParkingLotControlPoint {

    private int capacity = 0;
    // allocated size capacity + 1 to avoid 0 place num interpretation
    private String[] place2CarNumber = new String[1];
    private HashMap<String, Integer> carNumber2Place = new HashMap<>();
    private PriorityQueue<Integer> freePlaces = new PriorityQueue<>();

    public void command(String command, Function<String, String> callback) {
        final ParkingLotCommand parkingLotCommand = CommandInterpreter.instance.parse(command);
        final ParkingAction parkingAction = parkingLotCommand.getParkingAction();
        if (ParkingAction.undef.equals(parkingAction)) {
        } else if (ParkingAction.create.equals(parkingAction)) {
            Integer parkingLotSize = Integer.class.cast(parkingLotCommand.getArgument(0));
            create(parkingLotSize, callback);
        } else if (ParkingAction.park.equals(parkingAction)) {
            String carNumber = String.class.cast(parkingLotCommand.getArgument(0));
            park(carNumber, callback);
        } else if (ParkingAction.leave.equals(parkingAction)) {
            String carNumber = String.class.cast(parkingLotCommand.getArgument(0));
            Integer hours = Integer.class.cast(parkingLotCommand.getArgument(1));
            leave(carNumber, hours, callback);
        } else if (ParkingAction.status.equals(parkingAction)) {
            status(callback);
        }
    }

    public void create(int parkingLotSize, Function<String, String> callback) {
        parkingLotSize = parkingLotSize < 0 ? 0 : parkingLotSize;
        final int capacity = parkingLotSize;
        place2CarNumber = new String[capacity + 1];
        carNumber2Place = new HashMap<>(capacity);
        freePlaces = new PriorityQueue<>(capacity);
        for (int placeNum = 1; placeNum <= capacity; placeNum++) {
            place2CarNumber[placeNum] = null;
            freePlaces.add(placeNum);
        }
        this.capacity = capacity;
        final String msg = String.format("Created parking lot with %s slots", capacity);
        if (callback != null) {
            callback.apply(msg);
        }
    }

    public void park(String carNumber, Function<String, String> callback) {
        final Integer freePlaceNum = freePlaces.poll();
        if (freePlaceNum == null) {
            final String msg = String.format("Sorry, parking lot is full");
            if (callback != null) {
                callback.apply(msg);
            }
            return;
        }
        carNumber2Place.put(carNumber, freePlaceNum);
        place2CarNumber[freePlaceNum] = carNumber;
        final String msg = String.format("Allocated slot number: %s", freePlaceNum);
        if (callback != null) {
            callback.apply(msg);
        }
    }

    public void leave(String carNumber, int hours, Function<String, String> callback) {
        final Integer placeNum4Free = carNumber2Place.remove(carNumber);
        if (placeNum4Free == null) {
            final String msg = String.format("Registration Number %s not found", carNumber);
            if (callback != null) {
                callback.apply(msg);
            }
            return;
        }
        place2CarNumber[placeNum4Free] = null;
        freePlaces.add(placeNum4Free);
        final int charge = ParkingLotCharge.instance.calculate(hours);
        final String msg = String.format("Registration Number %s from Slot %s has left with Charge %s", carNumber, placeNum4Free, charge);
        if (callback != null) {
            callback.apply(msg);
        }
    }

    public void status(Function<String, String> callback) {
        String msg = String.format("Slot No. Registration No.");
        if (callback != null) {
            callback.apply(msg);
        }
        for (int i = 1; i <= capacity; i++) {
            final int slotNo = i;
            final String registrationNo = place2CarNumber[i];
            if (registrationNo == null) {
                continue;
            }
            msg = String.format("%s %s", StringUtils.rightPad(Integer.toString(slotNo), 8, " "), registrationNo);
            if (callback != null) {
                callback.apply(msg);
            }
        }
    }
}
