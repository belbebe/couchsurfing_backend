package hu.bme.aut.onlab.couchsurfing_backend.repository

import hu.bme.aut.onlab.couchsurfing_backend.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String): User

    fun findByUsername(username: String): User
}