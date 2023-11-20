package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.PaymentMethod;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.RoomType;
import lombok.*;

import jakarta.persistence.*;
import lombok.experimental.FieldNameConstants;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
@Getter
@Setter
@FieldNameConstants
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "longitude", nullable = false)
    private float geoLength;

    @Column(name = "latitude", nullable = false)
    private float geoWidth;

    @Column(name = "room_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "currency", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Column(name = "price_with_chores")
    private Float priceWithChores;

    @Column(name = "is_paying_with_chores_possible", nullable = false)
    private boolean payingWithChoresPossible;

    @Column(name = "is_paying_with_card_possible", nullable = false)
    private boolean payingWithCardPossible;

    @Column(name = "is_paying_with_cash_possible", nullable = false)
    private boolean payingWithCashPossible;

    @Column(name = "max_num_of_guests", nullable = false)
    private int maxGuestNum;

    @Column(name = "non_smoking", nullable = false)
    private boolean nonSmoking;

    @Column(name = "pet_friendly", nullable = false)
    private boolean petFriendly;

    @Column(name = "air_conditioner", nullable = false)
    private boolean airConditioner;

    @Column(name = "parking", nullable = false)
    private boolean parking;

    @Column(name = "bicycle_storage", nullable = false)
    private boolean bicycleStorage;

    @Column(name = "additional_info")
    private String additionalInfo;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "owner_id", insertable = false, updatable = false)
    private Long ownerId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "room")
    private Set<Booking> bookings = new HashSet<Booking>();

    public void assignUser(User user) {
        this.owner = user;
    }
}
