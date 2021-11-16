package com.dkatalis.free;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParkingLotCommand {

    private ParkingAction parkingAction = ParkingAction.undef;
    private List<String> arguments = Collections.emptyList();

    public ParkingLotCommand() {
    }

    public ParkingLotCommand(ParkingAction parkingAction, String... arguments) {
        this.parkingAction = parkingAction;
        this.arguments = Collections.unmodifiableList(Arrays.asList(arguments));
        verifyArgumentNum();
        verifyArgumentTypes();
    }

    private void verifyArgumentNum() {
        if (arguments.size() < parkingAction.numArgumentsMin()) {
            final String msg
                    = String.format("Arguments number for the command %s is less than expected %s."
                    , parkingAction.code(), parkingAction.numArgumentsMin());
            throw new ParkingLotCmdException(msg);
        }
    }

    private void verifyArgumentTypes() {
        final Class[] types = parkingAction.types;
        for (int i = 0; i < types.length; i++) {
            final Class type = types[i];
            final String argument = this.arguments.get(i);
            if (Integer.class.equals(type)) {
                try {
                    Integer.parseInt(argument);
                } catch (NumberFormatException ex) {
                    final String msg
                            = String.format("Command %s argument %s expected to be integer."
                            , parkingAction.code(), i);
                    throw new ParkingLotCmdException(msg);
                }
            } else if (String.class.equals(type)) {
            }
        }
    }

    public ParkingAction getParkingAction() {
        return parkingAction;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public Object getArgument(int place) {
        final Class[] types = parkingAction.types;
        if (place >= types.length || place >= arguments.size()) {
            return null;
        }
        final Class type = types[place];
        final String argument = this.arguments.get(place);
        if (Integer.class.equals(type)) {
            return Integer.parseInt(argument);
        } else if (String.class.equals(type)) {
            return argument;
        }
        return null;
    }
}
