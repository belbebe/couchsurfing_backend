package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

// szállások keresésére adott kérésben szereplő adatok
@Data
public class AccommodationRequest {
    @NotNull
    private String city;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private PaymentMethod[] paymentMethod;

    private Integer priceFrom;

    private Integer priceTo;

    private Currency currency;

    private Float ratingFrom;

    private Float ratingTo;

    @NotNull
    private Boolean nonSmoking;

    @NotNull
    private Boolean petFriendly;

    @NotNull
    private Boolean airConditioner;

    @NotNull
    private Boolean parking;

    @NotNull
    private Boolean bicycleStorage;

    // id of the user who searches the accommodations
    @NotNull
    private Integer tenantId;
}
