package com.bridgelabz;

/**
 * Purpose : To simulate ParkingLot System
 *
 * @author DAMINI CHANDRAKAR
 * @since 2021-11-11
 */
public class ParkingLotSystem {
    private Object vehicle;

    ParkingLotSystem() {

    }

    /**
     * Purpose : Create a method to print a message
     *
     * @return : Welcome message
     */
    public String printMessage() {
        return "Welcome to ParkingLot System";
    }

    /**
     * Purpose : Create method to park the vehicle
     *
     * @param vehicle object : Take vehicle object as parameter
     * @return True when vehicle is parked
     */
    public boolean park(Object vehicle) {
        if (this.vehicle != null) {
            return false;
        }
        this.vehicle = vehicle;
        return true;
    }

    /**
     * Purpose : Create method to unparkded the vehicle
     *
     * @param vehicle object : Take vehicle object as parameter
     * @return True when object is unpakerd
     */
    public boolean unPark(Object vehicle) {
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
