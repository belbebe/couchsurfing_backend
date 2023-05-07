package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.RoomType;
import lombok.Data;

import java.util.List;

@Data
public class RoomRequest {
    private String address;

    private float geoLength;

    private float geoWidth;

    private RoomType roomType;

    private float price;

    private Currency currency;

    private int maxGuestNum;

    private boolean nonSmoking;

    private boolean petFriendly;

    private boolean airConditioner;

    private boolean parking;

    private boolean bicycleStorage;

    private String additionalInfo;

    private int userId;
}
