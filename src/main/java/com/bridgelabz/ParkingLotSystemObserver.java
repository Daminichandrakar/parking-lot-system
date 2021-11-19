package com.bridgelabz;

/**
 * Purpose : Create an interface for observer
 *
 * @author DAMINI CHANDRAKAR
 * @since 2021-11-11
 */
public interface ParkingLotSystemObserver {
    public void parkingFull();
    public boolean isParkingFull();
    public boolean parkingAvailable();
}
