package hu.bme.aut.onlab.couchsurfing_backend.dto

import lombok.Data
import java.time.LocalDate

@Data
class UserDto(val id: Int, val fullName: String, val username: String?, birthDate: LocalDate,
              email: String, phone: String, password: String) {
}