package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.RoomType;
import lombok.Data;

import java.util.List;

@Data
public class RoomResponse {
    private int id;
    
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

    private List<BookingResponse> bookings;
}

// TODO: rating mező felvétele a db-be is, és a megfelelő helyekre (model, request, response)
