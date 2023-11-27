package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.exception;

import lombok.Getter;

@Getter
public class CanNotRateUserWithoutBookingHistoryException extends RuntimeException {
    private final String message;

    public CanNotRateUserWithoutBookingHistoryException(String message) { this.message = message; }
}
