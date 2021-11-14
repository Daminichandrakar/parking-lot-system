package com.bridgelabz;

/**
 * @author DAMINI CHANDRAKAR
 * @Purpose: To Inform Parking Full To Parking Owner
 * @since 2021-11-11
 */
public class AirportSecurity implements ParkingLotSystemObserver {
    private boolean parkingCapacity;

    /**
     * Purpose: To change Parking Capacity To True If Parking Is Full
     */
    public void parkingFull() {
        this.parkingCapacity = true;
    }

    /**
     * Purpose: To Return Parking Capacity
     *
     * @return true if parkingFull Or False
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
