package hu.bme.aut.onlab.couchsurfing_backend.dto

import lombok.Data

@Data
class PasswordDto(val oldPassword: String, val newPassword: String) {
}