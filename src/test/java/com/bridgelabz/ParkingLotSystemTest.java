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
        vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle, Vehicle.DriverType.NORMAL);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() {
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle3 = new Vehicle("BMW", "HR-26CK2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle4 = new Vehicle("Toyoto ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle2, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle3, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle4, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle4, Vehicle.DriverType.NORMAL);
        });
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnFalse() {
        vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle, Vehicle.DriverType.NORMAL);
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
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle3 = new Vehicle("BMW", "HR-26CK2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle4 = new Vehicle("Toyoto ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle5 = new Vehicle("Alto", "HR-26CK2784", "Red", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.registerParkingLotSystemObserver(parkingOwner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle2, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle3, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle4, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle5, Vehicle.DriverType.NORMAL);
        }, "Parking lot is full");
        Assertions.assertTrue(parkingOwner.isParkingFull());
    }

    @Test
    public void givenVehicle_WhenParkingFull_ShouldInformToAirportSecurity() {
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle3 = new Vehicle("BMW", "HR-26CK2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle4 = new Vehicle("Toyoto ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle5 = new Vehicle("Alto", "HR-26CK2784", "Red", "11:00", Vehicle.Size.LARGE);
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotSystemObserver(airportSecurity);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle2, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle3, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle4, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle5, Vehicle.DriverType.NORMAL);
        }, "Parking lot is full");
        Assertions.assertTrue(airportSecurity.isParkingFull());
    }

    @Test
    public void givenVehicle_WhenParkingAvailableAndOwnerIsObserver_ShouldInformOwner() {
        ParkingOwner parkingOwner = new ParkingOwner();
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("Ford ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle3 = new Vehicle("BMW", "HR-26CK2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle4 = new Vehicle("Toyota ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle5 = new Vehicle("Alto", "HR-26CK2784", "Red", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.registerParkingLotSystemObserver(parkingOwner);
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLotSystem.park(vehicle1, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle2, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle3, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle4, Vehicle.DriverType.NORMAL);
            parkingLotSystem.park(vehicle5, Vehicle.DriverType.NORMAL);
        });
        Assertions.assertTrue(parkingOwner.isParkingFull());
        parkingLotSystem.unPark(vehicle4);
        Assertions.assertFalse(parkingOwner.isParkingFull());
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleFound_ShouldReturnVehicleSlot() {
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle, Vehicle.DriverType.NORMAL);
        int lotNumber = parkingLotSystem.findVehicle(vehicle);
        Assertions.assertEquals(1, lotNumber);
    }

    @Test
    public void givenParkingLotSystem_WhenVehicleNotFound_ShouldReturnException() {
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00", Vehicle.Size.LARGE);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.findVehicle(vehicle));
    }

    @Test
    public void givenAVehicle_WhenParked_ThenCheckTimeOfParking_ShouldReturnParkingTime() {
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle, Vehicle.DriverType.NORMAL);
        String vehicleParkingTime = parkingLotSystem.getVehicleParkingTime(vehicle);
        Assertions.assertEquals("11:00", vehicleParkingTime);
    }

    @Test
    public void givenAVehicle_WhenNotParked_ThenCheckTimeOfParking_ShouldReturnException() {
        Vehicle vehicle = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00", Vehicle.Size.LARGE);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.getVehicleParkingTime(vehicle));
    }

    @Test
    public void givenAVehicle_ShouldParkedVehiclesEvenly() {
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "Red", "11:00", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("2 ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle1, Vehicle.DriverType.NORMAL);
        parkingLotSystem.park(vehicle2, Vehicle.DriverType.NORMAL);
        Assertions.assertEquals(1, parkingLotSystem.findVehicle(vehicle1));
        Assertions.assertEquals(2, parkingLotSystem.findVehicle(vehicle2));
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnThePositionOfWhiteColorVehicle() {
        Vehicle vehicle1 = new Vehicle("Lamborghini", "HR-26CF2784", "White", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle1, Vehicle.DriverType.NORMAL);
        Assertions.assertEquals(1, parkingLotSystem.getVehicleBYColour(vehicle1, "White"));
        Vehicle vehicle2 = new Vehicle("BMW ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle2, Vehicle.DriverType.NORMAL);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.getVehicleBYColour(vehicle2,
                "White"));
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnThePositionOfToyotaBlueColorVehicle() {
        Vehicle vehicle1 = new Vehicle("Toyota", "HR-26CF2784", "Blue", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle1, Vehicle.DriverType.NORMAL);
        Assertions.assertEquals(1, parkingLotSystem.getVehicleBYNameAndColour(vehicle1, "Toyota", "Blue"));
        Vehicle vehicle2 = new Vehicle("BMW ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle2, Vehicle.DriverType.NORMAL);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotSystem.getVehicleBYNameAndColour(vehicle2, "Toyota", "Blue"));
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnThePositionOfToyotaBlueColorVehicleWithNumberPlate() {
        Vehicle vehicle1 = new Vehicle("Toyota", "HR-26CF2784", "Blue", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle1, Vehicle.DriverType.NORMAL);
        Assertions.assertEquals(1, parkingLotSystem.getVehicleBYBlueColorToyotaWithNumberPlate(vehicle1, "Toyota", "Blue", "HR-26CF2784"));
        Vehicle vehicle2 = new Vehicle("BMW ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle2, Vehicle.DriverType.NORMAL);
        Assertions.assertThrows(ParkingLotException.class, () ->
                parkingLotSystem.getVehicleBYBlueColorToyotaWithNumberPlate(vehicle2, "Toyota", "Blue", "HR-26CF2784"));
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnThePositionOfBMWVehicle() {
        Vehicle vehicle1 = new Vehicle("BMW", "HR-26CF2784", "Blue", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle1, Vehicle.DriverType.NORMAL);
        Assertions.assertEquals("BMW", parkingLotSystem.getVehicleBYName(vehicle1, "BMW"));
        Vehicle vehicle2 = new Vehicle("Toyota ", "HR-28CG2784", "Red", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle2, Vehicle.DriverType.NORMAL);
        Assertions.assertThrows(ParkingLotException.class, () ->
                parkingLotSystem.getVehicleBYName(vehicle2, "BMW"));
    }

    @Test
    void givenAVehicle_whenParked_shouldValidatedForNumberPlate() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle("BMW", "HR-26CF2784", "Blue", "11:00"
                , Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle1, Vehicle.DriverType.NORMAL);
        boolean validityCheck = PoliceDepartment.validateVehicleNumber(vehicle1);
        Assertions.assertTrue(validityCheck);
    }

    @Test
    void givenAVehicle_whenParked_shouldReturnVehicleAtParticularTime() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle("BMW", "HR-26CF2784", "Blue", "11:00"
                , Vehicle.Size.LARGE);
        parkingLotSystem.park(vehicle1, Vehicle.DriverType.NORMAL);
        String result = parkingLotSystem.getVehicleBaseOnTime("11:00");
        Assertions.assertEquals("BMW",result);
    }

}
