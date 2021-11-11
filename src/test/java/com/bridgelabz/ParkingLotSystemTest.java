package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Purpose : To simulate ParkingLot System TestCase
 *
 * @author DAMINI CHANDRAKAR
 * @since 2021-11-11
 */
public class ParkingLotSystemTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem(1);
    }

    @Test
    public void givenWelcomeMessage_ShouldReturnMessage() {
        String result = parkingLotSystem.printMessage();
        Assert.assertEquals(result, "Welcome to ParkingLot System");
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals(e.getMessage(), "Parking lot is full");
        }

    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenAVehicleNull_WhenUnParked_ShouldReturnException() {
        try {
            parkingLotSystem.unPark(null);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Vehicle is not available", e.getMessage());
        }
    }

    @Test
    public void givenVehicle_WhenParkingFull_ShouldInformOwner() {
        ParkingOwner parkingOwner = new ParkingOwner();
        try {
            parkingLotSystem.registerParkingLotSystemObserver(parkingOwner);
            parkingLotSystem.park(new Object());
            parkingLotSystem.park(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertTrue(parkingOwner.isParkingFull());
        }
    }

    @Test
    public void givenVehicle_WhenParkingFull_ShouldInformToAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        try {
            parkingLotSystem.registerParkingLotSystemObserver(airportSecurity);
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            boolean parkingFull = airportSecurity.isParkingFull();
            Assert.assertTrue(parkingFull);
        }
    }
}
