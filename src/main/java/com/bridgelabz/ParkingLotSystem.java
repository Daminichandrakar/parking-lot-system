package com.bridgelabz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose : To simulate ParkingLot System
 *
 * @author DAMINI CHANDRAKAR
 * @since 2021-11-11
 */
public class ParkingLotSystem {
    private final int parkingCapacity;
    private List<Vehicle> vehicleList;
    private ArrayList<ParkingLotSystemObserver> parkingLotSystemObservers;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");

    public ParkingLotSystem(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
        initializeParkingLot();
        this.parkingLotSystemObservers = new ArrayList<ParkingLotSystemObserver>();
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
     * Purpose: Initialize The VehiclesList With Null Values
     */
    public void initializeParkingLot() {
        this.vehicleList = new ArrayList();
        for (int i = 0; i < this.parkingCapacity; i++) {
            vehicleList.add(i, null);
        }
    }

    /**
     * Purpose : Create method to park the vehicle
     *
     * @param vehicle object : Take vehicle object as parameter
     * @throws ParkingLotException when parking lot is full
     */
    public void park(Vehicle vehicle, Integer slot) throws ParkingLotException {
        if (this.vehicleList.size() == parkingCapacity && !this.vehicleList.contains(null)) {
            for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers)
                parkingLotSystemObserver.parkingFull();
            throw new ParkingLotException("Parking lot is full");
        }
        if (this.vehicleList.contains(vehicle)) {
            throw new ParkingLotException("Vehicle already exist");
        }
        this.vehicleList.set(slot, vehicle);
        vehicle.setParkingTime(LocalDateTime.now().format(format));
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is parked or not
     *
     * @param vehicle object : takes vehicle as parameter
     * @return vehicle is parked(true) or not(false)
     */
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicleList.contains(vehicle)) {
            return true;
        }
        return false;
    }

    /**
     * Purpose : Create method to unParked the vehicle
     *
     * @param vehicle object : Take vehicle object as parameter
     * @throws ParkingLotException when there is no vehicle to unParked
     */
    public void unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null) {
            throw new ParkingLotException("Vehicle is not available");
        }
        if (this.vehicleList.contains(vehicle)) {
            this.vehicleList.remove(vehicle);
            for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers) {
                parkingLotSystemObserver.parkingAvailable();
            }
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
        if (this.vehicleList.contains(vehicle)) {
            return false;
        }
        return true;
    }

    /**
     * Purpose: Add Observer Like Owner and Security In List
     *
     * @param observer To Add in the List
     */
    public void registerParkingLotSystemObserver(ParkingLotSystemObserver observer) {
        this.parkingLotSystemObservers.add(observer);
    }

    /**
     * Purpose: To Get List Of Empty Parking Slots
     *
     * @return List Of Slots
     */
    public List<Integer> getListOfEmptyParkingSlots() {
        List<Integer> emptyParkingSlotList = new ArrayList<>();
        for (int i = 0; i < parkingCapacity; i++) {
            if (this.vehicleList.get(i) == null) {
                emptyParkingSlotList.add(i);
            }
        }
        return emptyParkingSlotList;
    }

    /**
     * Purpose : To find the vehicle where it is parked in the lot
     *
     * @param vehicle : Take vehicle as a parameter so that we can find that vehicle is
     *                where parked in the lot
     * @return index of vehicle that we want to find
     */
    public int findVehicle(Object vehicle) {
        if (this.vehicleList.contains(vehicle)) {
            return this.vehicleList.indexOf(vehicle);
        }
        throw new ParkingLotException("Vehicle Is Not Available");
    }

    /**
     * Purpose: To find at what time vehicle is parked
     *
     * @param vehicle is passed as parameter to check the vehicle parking time
     * @return vehicle parked time in the form of string
     */
    public String getVehicleParkingTime(Vehicle vehicle) {
        if (isVehicleParked(vehicle)) {
            return vehicle.getParkingTime();
        }
        throw new ParkingLotException("Vehicle is not found");
    }

    /**
     * Purpose : This method is created to know the location of all parked white cars
     *
     * @param vehicle : takes vehicle as parameter for checking the particular color of parked vehicle is white
     * @return the index position of the particular vehicle to get back the location
     * @throws ParkingLotException : when no such white color vehicle is found
     */
    public int getWhiteColorVehiclePosition(Vehicle vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle) && vehicle.getVehicleColor().equals("White"))
            for (Vehicle vehiclePosition : vehicleList) {
                if (vehiclePosition.equals(vehicle))
                    return vehicleList.indexOf(vehiclePosition);
            }
        throw new ParkingLotException("No Such Vehicle Found");
    }

    /**
     * Purpose : This method is created to know the location of blue color Toyota vehicle
     *
     * @param vehicle : takes vehicle as parameter for checking the blue color Toyota vehicle's location
     * @return the index position of that particular vehicle
     * @throws ParkingLotException : when no such blue color Toyota vehicle is found
     */
    public int getBlueColorToyotaVehiclePosition(Vehicle vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle)
                && vehicle.getVehicleColor().equals("Blue")
                && vehicle.getName().equals("TOYOTA"))
            for (Vehicle position : vehicleList) {
                if (position.equals(vehicle))
                    return vehicleList.indexOf(position);
            }
        throw new ParkingLotException("No Such Vehicle Found");
    }
}
