package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.PaymentMethod;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RoomRequest {
    @NotBlank
    @Size(max = 255)
    private String address;

    @NotNull
    private Float geoLength;

    @NotNull
    private Float geoWidth;

    @NotNull
    private RoomType roomType;

    @NotNull
    private Float price;

    @NotNull
    private Currency currency;

    @NotNull
    private Boolean isPayingWithChoresPossible;

    private Float priceWithChores;

    @NotNull
    private Boolean isPayingWithCashIsPossible;

    @NotNull
    private Boolean isPayingWithCardIsPossible;

    @NotNull
    private Integer maxGuestNum;

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

    @Size(max = 1000)
    private String additionalInfo;

    @NotNull
    private Integer ownerId;
}
