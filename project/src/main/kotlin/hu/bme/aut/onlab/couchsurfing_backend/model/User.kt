package hu.bme.aut.onlab.couchsurfing_backend.model

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "User")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    var id: Int? = null

    @Column(name = "full_name", nullable = false)
    lateinit var fullName: String

    @Column(name="username", nullable = true, unique = true)
    var username: String? = null

    @Column(name="birth_date", nullable = false)
    lateinit var birthDate: LocalDate

    @Column(name="email_address", nullable = false, unique = true)
    lateinit var email: String

    @Column(name="phone_number", nullable = false, unique = true)
    lateinit var phonenumber: String

    @Column(name="password", nullable = false)
    lateinit var password: String

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId")
    val rooms: Set<Room> = HashSet<Room>()

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId")
    val bookings: Set<Booking> = HashSet<Booking>()

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "userId")
    lateinit var leaderboard: Leaderboard
}