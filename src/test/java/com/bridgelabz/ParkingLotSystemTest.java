package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

/**
 * Purpose : To simulate ParkingLot System TestCase
 *
 * @author DAMINI CHANDRAKAR
 * @since 2021-11-11
 */
public class ParkingLotSystemTest {

    @Test
    public void givenWelcomeMessage_ShouldReturnMessage() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        String result = parkingLotSystem.printMessage();
        Assert.assertEquals(result, "Welcome to ParkingLot System");
    }
}
