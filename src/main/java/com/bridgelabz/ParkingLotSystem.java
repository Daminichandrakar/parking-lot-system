package com.bridgelabz;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Purpose : To simulate ParkingLot System
 *
 * @author DAMINI CHANDRAKAR
 * @since 2021-11-11
 */
public class ParkingLotSystem {
    private final int parkingCapacity;
    private ArrayList<ParkingLotSystemObserver> parkingLotSystemObservers;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
    private Map<Integer, Vehicle> parkingLot1 = new HashMap<>();
    private Map<Integer, Vehicle> parkingLot2 = new HashMap<>();
    Map currentLot = parkingLot1;
    int slotOfLot1 = 0;
    int slotOfLot2 = 0;

    public ParkingLotSystem(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
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
     * Purpose : Create method to park the vehicle
     *
     * @param vehicle object : Take vehicle object as parameter
     * @throws ParkingLotException when parking lot is full or when a vehicle is already exist
     * @Param slot : Takenin which slot position we want to park our vehicle
     */
    public void park(Vehicle vehicle) throws ParkingLotException {
        if (this.slotOfLot1 == parkingCapacity && this.slotOfLot2 == parkingCapacity) {
            for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers)
                parkingLotSystemObserver.parkingFull();
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Parking lot is full");
        }
        if (this.parkingLot1.containsValue(vehicle) || this.parkingLot2.containsValue(vehicle)) {
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_EXIST, "Vehicle already exist");
        }
        this.evenlyParkedVehicle(vehicle);
    }

    /**
     * Purpose : This method is created to check
     * the vehicle is parked or not
     *
     * @param vehicle object : takes vehicle as parameter
     * @return vehicle is parked(true) or not(false)
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        if (this.parkingLot1.containsValue(vehicle) || this.parkingLot2.containsValue(vehicle)) {
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
    public void unPark(Vehicle vehicle) throws ParkingLotException {
        if (vehicle == null) {
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle is not available");
        }
        if (this.parkingLot1.containsValue(vehicle)) {
            for (int position : parkingLot1.keySet()) {
                if (parkingLot1.containsValue(vehicle)) {
                    parkingLot1.put(position, null);
                }
            }
        }
        if (this.parkingLot2.containsValue(vehicle)) {
            for (int position : parkingLot2.keySet()) {
                if (parkingLot2.containsValue(vehicle)) {
                    parkingLot2.put(position, null);
                }
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
    public boolean isVehicleUnParked(Vehicle vehicle) {
        return this.parkingLot1.containsValue(vehicle) || this.parkingLot2.containsValue(vehicle);
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
     * Purpose : To find the vehicle where it is parked in the lot
     *
     * @param vehicle : Take vehicle as a parameter so that we can find that vehicle is
     *                where parked in the lot
     * @return index of vehicle that we want to find
     */
    public int findVehicle(Vehicle vehicle) {
        for (Map.Entry<Integer, Vehicle> position : parkingLot1.entrySet()) {
            if (position.getValue().equals(vehicle)) {
                return position.getKey();
            }
        }
        for (Map.Entry<Integer, Vehicle> position : parkingLot2.entrySet()) {
            if (position.getValue().equals(vehicle)) {
                return position.getKey();
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle Is Not Available");
    }

    /**
     * Purpose: To find at what time vehicle is parked
     *
     * @param vehicle is passed as parameter to check the vehicle parking time
     * @return vehicle parked time in the form of string
     */
    public String getVehicleParkingTime(Vehicle vehicle) {
        for (Vehicle vehicle1 : parkingLot1.values()) {
            if (vehicle1.equals(vehicle)) {
                return vehicle1.getParkingTime();
            }
        }
        for (Vehicle vehicle1 : parkingLot2.values()) {
            if (vehicle1.equals(vehicle)) {
                return vehicle1.getParkingTime();
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "Vehicle is not found");
    }

    /**
     *Purpose : This method is create to evenly distribute a vehicle on a lot ;
     * @param vehicle : vehicle object is parked as a parameter
     */
    public void evenlyParkedVehicle(Vehicle vehicle) {
        if (currentLot == parkingLot1) {
            slotOfLot1 = slotOfLot1 + 1;
            parkingLot1.put(1, vehicle);
            currentLot = parkingLot2;
            return;
        }
        if (currentLot == parkingLot2) {
            slotOfLot2 = slotOfLot2 + 1;
            parkingLot2.put(2, vehicle);
            currentLot = parkingLot1;
            return;
        }
    }
}
