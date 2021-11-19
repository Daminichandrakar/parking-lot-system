package com.bridgelabz;

/**
 * Purpose : Create a class for parkingOwner to check parkingLot is full or not
 *
 * @author DAMINI CHANDRAKAR
 * @since 2021-11-11
 */
public class ParkingOwner implements ParkingLotSystemObserver{
    private boolean parkingCapacity;

    /**
     * Purpose : Method to check parkingLot capacity is full
     */
    public void parkingFull() {
        this.parkingCapacity = true;
    }

    /**
     * Purpose : Create a method to check parkingLot capacity is  full or not
     * @return true when parkingLotCapacity is full
     */
    public boolean isParkingFull() {
        return this.parkingCapacity;
    }

    /**
     * Purpose : To Check Parking Is Available
     * @return false if parking is available
     */
    @Override
    public boolean parkingAvailable() {
       return this.parkingCapacity = false;
    }
}
