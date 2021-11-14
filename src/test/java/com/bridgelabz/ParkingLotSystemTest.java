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
    Vehicle vehicle = null;
    private List<Integer> listOfEmptyParkingSlots;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(2);
    }

    @Test
    public void givenWelcomeMessage_ShouldReturnMessage() {
        String result = parkingLotSystem.printMessage();
        Assert.assertEquals(result, "Welcome to ParkingLot System");
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "11:00");
        parkingLotSystem.park(vehicle, 0);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() {
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "11:00");
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "11:15");
        Assert.assertThrows("Parking lot is full", ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle, 0);
            parkingLotSystem.park(vehicle, 1);
        });
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "11:40");
        parkingLotSystem.park(vehicle, 0);
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
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "11:00");
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "11:15");
        Vehicle vehicle3 = new Vehicle("Ferrari", "HR-26CK2784", "11:20");
        parkingLotSystem.registerParkingLotSystemObserver(parkingOwner);
        Assert.assertThrows("Parking lot is full", ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1, 0);
            parkingLotSystem.park(vehicle2, 1);
            parkingLotSystem.park(vehicle3, 2);
        });
        Assert.assertTrue(parkingOwner.isParkingFull());
    }

    @Test
    public void givenVehicle_WhenParkingFull_ShouldInformToAirportSecurity() {
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "11:00");
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "11:15");
        Vehicle vehicle3 = new Vehicle("Ferrari", "HR-26CK2784", "11:20");
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotSystemObserver(airportSecurity);
        Assert.assertThrows("Parking lot is full", ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1, 0);
            parkingLotSystem.park(vehicle2, 1);
            parkingLotSystem.park(vehicle3, 2);
        });
        Assert.assertTrue(airportSecurity.isParkingFull());
    }

    @Test
    public void givenVehicle_WhenParkingAvailableAndOwnerIsObserver_ShouldInformOwner() {
        ParkingOwner parkingOwner = new ParkingOwner();
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "11:00");
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "11:15");
        Vehicle vehicle3 = new Vehicle("Ferrari", "HR-26CK2784", "11:20");
        parkingLotSystem.registerParkingLotSystemObserver(parkingOwner);
        Assert.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1, 0);
            parkingLotSystem.park(vehicle2, 1);
            parkingLotSystem.park(vehicle3, 2);
        });
        Assert.assertTrue(parkingOwner.isParkingFull());
        parkingLotSystem.unPark(vehicle1);
        Assert.assertFalse(parkingOwner.isParkingFull());
    }

    @Test
    public void givenParkingLotSystem_WhenListOfEmptySlotsCalled_ShouldReturnAvailableSlots() {
        listOfEmptyParkingSlots = parkingLotSystem.getListOfEmptyParkingSlots();
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "11:00");
        parkingLotSystem.park(vehicle, 0);
        listOfEmptyParkingSlots = parkingLotSystem.getListOfEmptyParkingSlots();
        Assert.assertEquals(1, listOfEmptyParkingSlots.size());
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleFound_ShouldReturnVehicleSlot() {
        listOfEmptyParkingSlots = parkingLotSystem.getListOfEmptyParkingSlots();
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "11:00");
        parkingLotSystem.park(vehicle, 0);
        int slotNumber = parkingLotSystem.findVehicle(vehicle);
        Assert.assertEquals(0, slotNumber);
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleNotFound_ShouldReturnException() {
        listOfEmptyParkingSlots = parkingLotSystem.getListOfEmptyParkingSlots();
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "11:00");
        Assert.assertThrows("Vehicle Is Not Available", ParkingLotException.class, () -> parkingLotSystem.findVehicle(vehicle));
    }

    @Test
    public void givenAVehicle_WhenParked_ThenCheckTimeOfParking_ShouldReturnParkingTime() {
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "11:00");
        parkingLotSystem.park(vehicle, 0);
        String vehicleParkingTime = parkingLotSystem.getVehicleParkingTime(vehicle);
        Assert.assertEquals("11:00", vehicleParkingTime);
    }
}
