package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.RoomType;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomResponse {
    private int id;
    
    private String address;

    private Float geoLength;

    private Float geoWidth;

    private RoomType roomType;

    private Float price;

    private Currency currency;

    private boolean payingWithChoresPossible;

    private Float priceWithChores;

    private boolean payingWithCashPossible;

    private boolean payingWithCardPossible;

    private Integer maxGuestNum;

    private Boolean nonSmoking;
   
    private Boolean petFriendly;

    private Boolean airConditioner;

    private Boolean parking;

    private Boolean bicycleStorage;

    private String additionalInfo;

    private Integer ownerId;

    private List<BookingResponse> bookings;
}
