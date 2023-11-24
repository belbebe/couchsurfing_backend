package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.exception;

import lombok.Getter;

@Getter
public class BookingOwnRoomException extends RuntimeException {
    private final String message;

    public BookingOwnRoomException(String message) { this.message = message; }
}
