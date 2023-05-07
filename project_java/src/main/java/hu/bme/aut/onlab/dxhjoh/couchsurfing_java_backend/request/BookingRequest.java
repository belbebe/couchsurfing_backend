package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.PaymentMethod;

import java.time.LocalDate;

public class BookingRequest {

    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private PaymentMethod paymentMethod;

    private boolean approved;

    private int numOfGuests;

    private String additionalNotes;

    private boolean payWithChores;

    private float totalPrice;

    private int renterUserId;

    private int roomId;
}
