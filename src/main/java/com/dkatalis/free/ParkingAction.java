package com.dkatalis.free;

public enum ParkingAction {
    undef(),
    create("create", "`create [size]` - Creates parking lot of size n", new Class[]{Integer.class}),
    park("park", "`park [car-number]` - Parks a car", new Class[]{String.class}),
    leave("leave", "`leave [car-number] [hours]` - Removes (unpark) a car", new Class[]{String.class, Integer.class}),
    status("status", "`status` - Prints status of the parking lot"),
    ;

    final String code;
    final String description;
    final Class[] types;

    ParkingAction() {
        this("", "", new Class[]{});
    }

    ParkingAction(String code, String description) {
        this(code, description, new Class[]{});
    }

    ParkingAction(String code, String description, Class[] types) {
        this.code = code;
        this.description = description;
        this.types = types;
    }

    public String code() {
        return code;
    }

    public int numArgumentsMin() {
        return types.length;
    }

    public static final ParkingAction fromStr(String name) {
        ParkingAction res = ParkingAction.undef;
        for (ParkingAction tmp : ParkingAction.values()) {
            if (tmp.name().equalsIgnoreCase(name)
                    || tmp.code.equalsIgnoreCase(name)) {
                res = tmp;
                break;
            }
        }
        return res;
    }
}
