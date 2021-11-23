package com.bridgelabz;

import java.awt.*;

/**
 * @author DAMINI CHANDRAKAR
 * @Purpose: Create a vehicle class which contains vehicle details
 * @since 2021-11-11
 */
public class Vehicle {
    private String name;
    private String vehicleNumber;
    private String parkingTime;
    private String vehicleColor;


    public Vehicle(String name, String vehicleNumber, String vehicleColor, String parkingTime) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.vehicleColor = vehicleColor;
        this.parkingTime = parkingTime;
    }

    public String getName() {
        return name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getParkingTime() {
        return parkingTime;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", parkingTime='" + parkingTime + '\'' +
                ", vehicleColor='" + vehicleColor + '\'' +
                '}';
    }

}

