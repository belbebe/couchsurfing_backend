package hu.bme.aut.onlab.couchsurfing_backend.model

import lombok.Getter
import lombok.Setter
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "User")
@Getter
@Setter
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Int? = null

    @Column(name = "full_name", nullable = false)
    val fullName: String

    @Column(name="username", nullable = true, unique = true)
    val username: String? = null

    @Column(name="birth_date", nullable = false)
    val birthDate: LocalDate

    @Column(name="email_address", nullable = false)
    val email: String

    @Column(name="phone_number", nullable = false)
    val phonenumber: String

    @Column(name="password", nullable = false)
    val password: String
}