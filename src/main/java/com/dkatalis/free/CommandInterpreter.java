package com.dkatalis.free;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum CommandInterpreter {

    instance;

    public ParkingLotCommand parse(String command) {
        command = StringUtils.trimToEmpty(command);
        if (StringUtils.isEmpty(command)) {
            return new ParkingLotCommand();
        }
        final String[] commandArgs = StringUtils.split(command);
        final String[] arguments = Arrays.copyOfRange(commandArgs, 1, commandArgs.length);
        final ParkingAction parkingAction = ParkingAction.fromStr(commandArgs[0]);
        final ParkingLotCommand res = new ParkingLotCommand(parkingAction, arguments);
        return res;
    }
}
