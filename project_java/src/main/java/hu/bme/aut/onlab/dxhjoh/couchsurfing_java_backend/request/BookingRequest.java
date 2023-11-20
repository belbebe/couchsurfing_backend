package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.PaymentMethod;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {

    private int id; // ez kell ide?

    private LocalDate startDate;

    private LocalDate endDate;

    private PaymentMethod paymentMethod;

    private boolean approved; // ez kell ide?

    private int numOfGuests;

    private String additionalNotes;

    private boolean payWithChores;

    private float totalPrice;

    // TODO: currency mező DB-ben is

    private int renterUserId;

    private int roomId;
}
