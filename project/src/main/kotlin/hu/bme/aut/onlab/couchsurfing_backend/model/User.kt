package hu.bme.aut.onlab.couchsurfing_backend.model

import lombok.NoArgsConstructor
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "User")
@NoArgsConstructor
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

    @Column(name="email_address", nullable = false)
    lateinit var email: String

    @Column(name="phone_number", nullable = false)
    lateinit var phonenumber: String

    @Column(name="password", nullable = false)
    lateinit var password: String
}