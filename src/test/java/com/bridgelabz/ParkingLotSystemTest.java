package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Purpose : To simulate ParkingLot System TestCase
 *
 * @author DAMINI CHANDRAKAR
 * @since 2021-11-11
 */
public class ParkingLotSystemTest {

    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    private List<Integer> listOfEmptyParkingSlots;

    @Before
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem(2);
    }

    @Test
    public void givenWelcomeMessage_ShouldReturnMessage() {
        String result = parkingLotSystem.printMessage();
        Assert.assertEquals(result, "Welcome to ParkingLot System");
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        parkingLotSystem.park(vehicle , 0);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() {
        Assert.assertThrows("Parking lot is full", ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle , 0) ;
            parkingLotSystem.park(vehicle , 1);
        });
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.park(vehicle , 0);
        Assert.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
        parkingLotSystem.unPark(vehicle);
        Assert.assertTrue(parkingLotSystem.isVehicleUnParked(vehicle));
    }

    @Test
    public void givenAVehicleNull_WhenUnParked_ShouldReturnException() {
        Assert.assertThrows(ParkingLotException.class, () -> parkingLotSystem.unPark(null));
    }

    @Test
    public void givenVehicle_WhenParkingFull_ShouldInformOwner() {
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLotSystem.registerParkingLotSystemObserver(parkingOwner);
        Assert.assertThrows("Parking lot is full", ParkingLotException.class, () -> {
            parkingLotSystem.park(new Object(),0);
            parkingLotSystem.park(vehicle,1);
            parkingLotSystem.park(vehicle,2);
        });
        Assert.assertTrue(parkingOwner.isParkingFull());
    }

    @Test
    public void givenVehicle_WhenParkingFull_ShouldInformToAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotSystemObserver(airportSecurity);
        Assert.assertThrows("Parking lot is full", ParkingLotException.class, () -> {
            parkingLotSystem.park(new Object(),0);
            parkingLotSystem.park(vehicle , 1);
            parkingLotSystem.park(vehicle,2);
        });
        Assert.assertTrue(airportSecurity.isParkingFull());
    }

    @Test
    public void givenVehicle_WhenParkingAvailableAndOwnerIsObserver_ShouldInformOwner() {
        ParkingOwner parkingOwner = new ParkingOwner();
        parkingLotSystem.registerParkingLotSystemObserver(parkingOwner);
        Assert.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle , 0);
            parkingLotSystem.park(new Object() , 1);
            parkingLotSystem.park(new Object() , 2);
        });
        Assert.assertTrue(parkingOwner.isParkingFull());
        parkingLotSystem.unPark(vehicle);
        Assert.assertFalse(parkingOwner.isParkingFull());
    }


    @Test
    public void givenParkingLotSystem_WhenListOfEmptySlotsCalled_ShouldReturnAvailableSlots() {
        listOfEmptyParkingSlots = parkingLotSystem.getListOfEmptyParkingSlots();
        parkingLotSystem.park(vehicle, 0);
        listOfEmptyParkingSlots = parkingLotSystem.getListOfEmptyParkingSlots();
        Assert.assertEquals(1, listOfEmptyParkingSlots.size());
    }
}
