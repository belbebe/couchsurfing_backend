package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.PaymentMethod;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    private LocalDate startDate;

    private LocalDate endDate;

    private PaymentMethod paymentMethod;

    private int numOfGuests;

    private String additionalNotes;

    private boolean payWithChores;

    private float totalPrice;

    private Currency currency;

    private int renterUserId;

    private int roomId;
}
