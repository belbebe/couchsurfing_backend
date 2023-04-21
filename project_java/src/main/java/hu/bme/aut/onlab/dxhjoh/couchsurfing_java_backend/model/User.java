package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId")
    private Set<Room> rooms = new HashSet<Room>();

    /*
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId")
    private Set<Booking> bookings = new HashSet<Booking>();

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userId")
    private Leaderboard leaderboardPlace;
     */
}
