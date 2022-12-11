package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.exception;

import lombok.Getter;

@Getter
public class CouchsurfingRuntimeException extends RuntimeException {
    private final String message;

    public CouchsurfingRuntimeException(String message) { this.message = message; }
}
