package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @BeforeEach
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem(2);
    }

    @Test
    public void givenWelcomeMessage_ShouldReturnMessage() {
        String result = parkingLotSystem.printMessage();
        Assertions.assertEquals(result, "Welcome to ParkingLot System");
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00");
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() {
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00");
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "Red", "11:00");
        Vehicle vehicle3 = new Vehicle("BMW", "HR-26CK2784", "Red", "11:00");
        Vehicle vehicle4 = new Vehicle("Toyoto ", "HR-28CG2784", "Red", "11:00");
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle3);
            parkingLotSystem.park(vehicle4);
            parkingLotSystem.park(vehicle4);
        });
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnFalse() {
        vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00");
        parkingLotSystem.park(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
        parkingLotSystem.unPark(vehicle);
        Assertions.assertFalse(parkingLotSystem.isVehicleUnParked(vehicle));
    }

    @Test
    public void givenAVehicleNull_WhenUnParked_ShouldReturnException() {
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.unPark(null));
    }

    @Test
    public void givenVehicle_WhenParkingFull_ShouldInformOwner() {
        ParkingOwner parkingOwner = new ParkingOwner();
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00");
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "Red", "11:00");
        Vehicle vehicle3 = new Vehicle("BMW", "HR-26CK2784", "Red", "11:00");
        Vehicle vehicle4 = new Vehicle("Toyoto ", "HR-28CG2784", "Red", "11:00");
        Vehicle vehicle5 = new Vehicle("Alto", "HR-26CK2784", "Red", "11:00");
        parkingLotSystem.registerParkingLotSystemObserver(parkingOwner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle3);
            parkingLotSystem.park(vehicle4);
            parkingLotSystem.park(vehicle5);
        }, "Parking lot is full");
        Assertions.assertTrue(parkingOwner.isParkingFull());
    }

    @Test
    public void givenVehicle_WhenParkingFull_ShouldInformToAirportSecurity() {
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00");
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "Red", "11:00");
        Vehicle vehicle3 = new Vehicle("BMW", "HR-26CK2784", "Red", "11:00");
        Vehicle vehicle4 = new Vehicle("Toyoto ", "HR-28CG2784", "Red", "11:00");
        Vehicle vehicle5 = new Vehicle("Alto", "HR-26CK2784", "Red", "11:00");
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotSystemObserver(airportSecurity);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle3);
            parkingLotSystem.park(vehicle4);
            parkingLotSystem.park(vehicle5);
        }, "Parking lot is full");
        Assertions.assertTrue(airportSecurity.isParkingFull());
    }

    @Test
    public void givenVehicle_WhenParkingAvailableAndOwnerIsObserver_ShouldInformOwner() {
        ParkingOwner parkingOwner = new ParkingOwner();
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00");
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "Red", "11:00");
        Vehicle vehicle3 = new Vehicle("BMW", "HR-26CK2784", "Red", "11:00");
        Vehicle vehicle4 = new Vehicle("Toyoto ", "HR-28CG2784", "Red", "11:00");
        Vehicle vehicle5 = new Vehicle("Alto", "HR-26CK2784", "Red", "11:00");
        parkingLotSystem.registerParkingLotSystemObserver(parkingOwner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(vehicle2);
            parkingLotSystem.park(vehicle3);
            parkingLotSystem.park(vehicle4);
            parkingLotSystem.park(vehicle5);
        });
        Assertions.assertTrue(parkingOwner.isParkingFull());
        parkingLotSystem.unPark(vehicle4);
        Assertions.assertFalse(parkingOwner.isParkingFull());
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleFound_ShouldReturnVehicleSlot() {
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00");
        parkingLotSystem.park(vehicle);
        int lotNumber = parkingLotSystem.findVehicle(vehicle);
        Assertions.assertEquals(1, lotNumber);
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleNotFound_ShouldReturnException() {
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00");
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.findVehicle(vehicle));
    }

    @Test
    public void givenAVehicle_WhenParked_ThenCheckTimeOfParking_ShouldReturnParkingTime() {
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00");
        parkingLotSystem.park(vehicle);
        String vehicleParkingTime = parkingLotSystem.getVehicleParkingTime(vehicle);
        Assertions.assertEquals("11:00", vehicleParkingTime);
    }

    @Test
    public void givenAVehicle_WhenNotParked_ThenCheckTimeOfParking_ShouldReturnException() {
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00");
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.getVehicleParkingTime(vehicle));
    }

    @Test
    public void givenAVehicle_ShouldParkedVehiclesEvenly(){
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00");
        Vehicle vehicle2 = new Vehicle("2 ", "HR-28CG2784", "Red", "11:00");
        parkingLotSystem.park(vehicle1);
        parkingLotSystem.park(vehicle2);
        Assertions.assertEquals(1, parkingLotSystem.findVehicle(vehicle1));
        Assertions.assertEquals(2, parkingLotSystem.findVehicle(vehicle2));
    }
}
