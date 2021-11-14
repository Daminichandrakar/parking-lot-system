package com.bridgelabz;

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
    private Vehicle vehicle;

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
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is parked or not
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
     * @param vehicle object : Take vehicle object as parameter
     * @throws ParkingLotException when there is no vehicle to unParked
     */
    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (vehicle == null) {
            throw new ParkingLotException("Vehicle is not available");
        }
        if (this.vehicleList.contains(vehicle)) {
            this.vehicleList.remove(vehicle);
            for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers)
                parkingLotSystemObserver.parkingAvailable();
            return true;
        }
        throw new ParkingLotException("Vehicle is not available");
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is unParked or not
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
     * Purpose : To find the vehicle
     *
     * @param vehicle : Take vehicle as a parameter
     * @return index of vehicle that we want to find
     */
    public int findVehicle(Object vehicle) {
        if (this.vehicleList.contains(vehicle)) {
            return this.vehicleList.indexOf(vehicle);
        }
        throw new ParkingLotException("Vehicle Is Not Available");
    }

    /**
     * Purpose: To Find At what Time Vehicle Was Parked
     * @param vehicle is passed as parameter
     * @return vehicle parked time
     */
    public String getVehicleParkingTime(Vehicle vehicle) {
        if (isVehicleParked(vehicle)) {
            return vehicle.getParkingTime();
        }
        return null;
    }

}
