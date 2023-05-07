package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "couchsurfing_user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name="user_name", unique = true)
    private String username;

    @Column(name="birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name="email_address", nullable = false, unique = true)
    private String email;

    @Column(name="phone_number", nullable = false, unique = true)
    private String phone;

    @Column(name="user_password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Room> rooms = new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Booking> bookings = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Leaderboard leaderboardPlace;
}
