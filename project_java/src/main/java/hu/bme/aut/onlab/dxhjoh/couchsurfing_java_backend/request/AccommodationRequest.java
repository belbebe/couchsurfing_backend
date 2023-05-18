package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.PaymentMethod;
import lombok.Data;

import java.time.LocalDate;

// szállások keresésére adott kérésben szereplő adatok
@Data
public class AccommodationRequest {
    private String city;

    private LocalDate startDate;

    private LocalDate endDate;

    private PaymentMethod paymentMethod;

    private int priceFrom;

    private int priceTo;

    private Currency currency;

    private float ratingFrom;

    private float ratingTo;

    private boolean nonSmoking;

    private boolean petFriendly;

    private boolean airConditioner;

    private boolean parking;

    private boolean bicycleStorage;
}
