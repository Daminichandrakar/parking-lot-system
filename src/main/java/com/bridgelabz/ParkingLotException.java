package com.bridgelabz;

/**
 * Purpose : Create a class to throw a custom exception
 *
 * @author DAMINI CHANDRAKAR
 * @since 2021-11-11
 */
public class ParkingLotException extends RuntimeException {
    public ExceptionType exceptionType;

    public ParkingLotException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public enum ExceptionType {PARKING_LOT_IS_FULL, NO_SUCH_VEHICLE, VEHICLE_ALREADY_EXIST}
}
