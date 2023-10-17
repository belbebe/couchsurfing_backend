package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
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
    private float geoLength;

    @NotNull
    private float geoWidth;

    @NotNull
    private RoomType roomType;

    @NotNull
    private float price;

    @NotNull
    private Currency currency;

    @NotNull
    private int maxGuestNum;

    @NotNull
    private boolean nonSmoking;

    @NotNull
    private boolean petFriendly;

    @NotNull
    private boolean airConditioner;

    @NotNull
    private boolean parking;

    @NotNull
    private boolean bicycleStorage;

    @Size(max = 255)
    private String additionalInfo;

    @NotNull
    private int userId;
}
