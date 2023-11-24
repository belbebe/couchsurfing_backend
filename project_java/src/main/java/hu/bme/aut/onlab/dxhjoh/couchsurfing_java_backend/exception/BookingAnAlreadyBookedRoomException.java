package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.exception;

import lombok.Getter;

@Getter
public class BookingAnAlreadyBookedRoomException extends RuntimeException {
    private final String message;

    public BookingAnAlreadyBookedRoomException(String message) { this.message = message; }
}
