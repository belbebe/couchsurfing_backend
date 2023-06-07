package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.RoomType;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
@Getter
@Setter
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

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "currency", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Currency currency;

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

    @Column(name = "bicylce_storage", nullable = false)
    private boolean bicycleStorage;

    @Column(name = "additional_info")
    private String additionalInfo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "room")
    private Set<Booking> bookings = new HashSet<Booking>();

    public void assignUser(User user) {
        this.user = user;
    }
}
