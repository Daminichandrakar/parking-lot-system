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
     * @throws ParkingLotException when parking lot is full
     *
     */
    public void park(Object vehicle) throws ParkingLotException {
        if (this.vehicle != null) {
            throw new ParkingLotException("Parking lot is full");
        }
        this.vehicle = vehicle;
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is parked or not
     *
     * @param vehicle object : takes vehicle as parameter
     * @return  vehicle is parked(true) or not(false)
     */
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle)) {
            return true;
        }
        return false;
    }


    /**
     * Purpose : Create method to unParked the vehicle
     *
     * @param vehicle object : Take vehicle object as parameter
     * @throws  ParkingLotException when there is no vehicle to unParked
     */
    public void unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null) throw new ParkingLotException("Vehicle cannot be null");
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
        }
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is unParked or not
     *
     * @param vehicle : takes vehicle as parameter
     * @return the vehicle is unParked
     */
    public boolean isVehicleUnParked(Object vehicle) {
        if (this.vehicle == null) {
            return true;
        }
        return false;
    }

}
