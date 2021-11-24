package com.bridgelabz;

import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public Map<Integer, Vehicle> parkingLot1 = new HashMap<>();
    public Map<Integer, Vehicle> parkingLot2 = new HashMap<>();
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
     * @Param slot : Takeing which slot position we want to park our vehicle
     */
    public void park(Vehicle vehicle , Vehicle.DriverType driverType) throws ParkingLotException {
        if (this.slotOfLot1 == parkingCapacity && this.slotOfLot2 == parkingCapacity) {
            for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers)
                parkingLotSystemObserver.parkingFull();
            throw new ParkingLotException(ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Parking lot is full");
        }
        if (this.parkingLot1.containsValue(vehicle) || this.parkingLot2.containsValue(vehicle)) {
            throw new ParkingLotException(ParkingLotException.ExceptionType.VEHICLE_ALREADY_EXIST, "Vehicle already exist");
        }
        if (driverType.equals(Vehicle.DriverType.HANDICAPED)){
            this.handicappedPark(vehicle);
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
                    for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers)
                        parkingLotSystemObserver.parkingAvailable();
                }
            }
        }
        if (this.parkingLot2.containsValue(vehicle)) {
            for (int position : parkingLot2.keySet()) {
                if (parkingLot2.containsValue(vehicle)) {
                    parkingLot2.put(position, null);
                    for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers)
                        parkingLotSystemObserver.parkingAvailable();
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
     * @param vehicle : Take vehicle as a parameter so that we can find that vehicle is where parked in the lot
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
     * Purpose : This method is create to evenly distribute a vehicle on a lot ;
     *
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

    /**
     * Purpose : This method is created to know the location of all parked white cars
     *
     * @param vehicle : takes vehicle as parameter for checking the particular color of parked vehicle is white
     * @return the index position of the particular vehicle to get back the location
     * @throws ParkingLotException : when no such white color vehicle is found
     */
    public int getVehicleBYColour(Vehicle vehicle, String color) {
        if (this.parkingLot1.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot1.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color)) {
                    return vehicleMap.getKey();
                }
            }
        } else if (this.parkingLot2.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot2.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color)) {
                    return vehicleMap.getKey();
                }
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE,
                "vehicle not found");
    }

    public int getVehicleBYNameAndColour(Vehicle vehicle, String name, String color) {
        if (this.parkingLot1.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot1.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color) && vehicleMap.getValue().getName().equalsIgnoreCase(name)) {
                    return vehicleMap.getKey();
                }
            }
        } else if (this.parkingLot2.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot2.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color) && vehicleMap.getValue().getName().equalsIgnoreCase(name)) {
                    return vehicleMap.getKey();
                }
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.WHITE_CAR_NOT_FOUND,
                "vehicle not found");
    }

    /**
     * Purpose : This method is created to know the vehicle plate number of blue color Toyota vehicle
     *
     * @param vehicle : takes vehicle as parameter for checking the blue color Toyota vehicle's plate number
     * @return the vehicle number of that particular vehicle
     * @throws ParkingLotException : when no such blue color Toyota is found
     */
    public int getVehicleBYBlueColorToyotaWithNumberPlate(Vehicle vehicle, String name, String color, String numberPlate) {
        if (this.parkingLot1.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot1.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color) &&
                        vehicleMap.getValue().getName().equalsIgnoreCase(name) &&
                        vehicleMap.getValue().getVehicleNumber().equalsIgnoreCase(numberPlate)) {
                    return vehicleMap.getKey();
                }
            }
        } else if (this.parkingLot2.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot2.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color)
                        && vehicleMap.getValue().getName().equalsIgnoreCase(name)
                        && vehicleMap.getValue().getVehicleNumber().equalsIgnoreCase(numberPlate)) {
                    return vehicleMap.getKey();
                }
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE,
                "vehicle not found");
    }

    public String getVehicleBYName(Vehicle vehicle, String name) {
        if (this.parkingLot1.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot1.entrySet()) {
                if (vehicleMap.getValue().getName().equalsIgnoreCase(name)) {
                    return vehicleMap.getValue().getName();
                }
            }
        } else if (this.parkingLot2.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot2.entrySet()) {
                if (vehicleMap.getValue().getName().equalsIgnoreCase(name)) {
                    return vehicleMap.getValue().getName();
                }
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE,
                "vehicle not found");
    }

    private void handicappedPark(Vehicle vehicle) throws ParkingLotException {
        for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot1.entrySet()) {
            if (vehicleMap.getValue() == null) {
                parkingLot1.put(vehicleMap.getKey(), vehicle);
            }
        }
        if (!parkingLot1.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot2.entrySet()) {
                if (vehicleMap.getValue() == null) {
                    parkingLot2.put(vehicleMap.getKey(), vehicle);
                }
            }

        }

    }
}