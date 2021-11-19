package com.bridgelabz;

/**
 * @author DAMINI CHANDRAKAR
 * @Purpose: Create a vehicle class which contains vehicle details
 * @since 2021-11-11
 */
public class Vehicle {
    private  String name;
    private  String vehicleNumber;
    private String parkingTime;

    public Vehicle(String name, String vehicleNumber) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        //this.parkingTime = LocalTime.now();
    }

    public String getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(String parkingTime) {
        this.parkingTime = parkingTime;
    }

}
