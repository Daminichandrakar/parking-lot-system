package com.bridgelabz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PoliceDepartment {
    /**
     * Purpose :
     * @param vehicle
     * @return
     */
    public static boolean validateVehicleNumber(Vehicle vehicle) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[-][0-9]{2}[A-Z]{2}[0-9]{2,4}$");
        Matcher matcher = pattern.matcher(vehicle.getVehicleNumber());
        return matcher.matches();
    }
}
