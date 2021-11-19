package com.bridgelabz;

/**
 * Purpose : Create a class to throw a custom exception
 * @author DAMINI CHANDRAKAR
 * @since 2021-11-11
 */
public class ParkingLotException extends RuntimeException {
    public ParkingLotException(String message) {
        super(message);
    }
}
