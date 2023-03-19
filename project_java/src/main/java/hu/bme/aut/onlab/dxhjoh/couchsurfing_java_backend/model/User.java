package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name="username", nullable = true, unique = true)
    private String username;

    @Column(name="birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name="email_address", nullable = false, unique = true)
    private String email;

    @Column(name="phone_number", nullable = false, unique = true)
    private String phone;

    @Column(name="password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId")
    private Set<Room> rooms = new HashSet<Room>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId")
    private Set<Booking> bookings = new HashSet<Booking>();

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userId")
    private Leaderboard leaderboardPlace;
}
