package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Room")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "geographical_length", nullable = false)
    private float geoLength;

    @Column(name = "geographical_width", nullable = false)
    private float geoWidth;

    @Column(name = "room_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "currency", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Column(name = "max_guest_num", nullable = false)
    private int maxGuestNum;

    @Column(name = "non_smoking", nullable = false)
    private boolean nonSmoking;

    @Column(name = "pet_friendly", nullable = false)
    private boolean petFriendly;

    @Column(name = "air_conditioner", nullable = false)
    private boolean airConditioner;

    @Column(name = "parking", nullable = false)
    private boolean parking;

    @Column(name = "bicylce_storage", nullable = false)
    private boolean bicycleStorage;

    @Column(name = "additonal_info")
    private String additionalInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
