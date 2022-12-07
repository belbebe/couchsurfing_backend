package hu.bme.aut.onlab.couchsurfing_backend.mapper

import hu.bme.aut.onlab.couchsurfing_backend.model.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class UserMapper(@Autowired private var passwordEncoder: PasswordEncoder) {

    @Mapping(expression = "java(getRooms(entity))", target = "rooms")
    @Mapping(target = "password", ignore = true) // hibát dob: Repeatable annotations with non-SOURCE retention are not yet supported
    // elvileg ez is nyelvi sajátosság, hogy JDK 1.6-ra targetál a Kotlin, és ott nem lehet ilyen repeatable annotációkat használni
    // https://stackoverflow.com/questions/44543978/kotlin-repeatable-annotations-dont-work-on-jdk-8
    abstract fun toDto(entity: User)
}