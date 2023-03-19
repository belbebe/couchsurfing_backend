package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.dto.UserDto;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(expression = "java(getRooms(entity))", target = "rooms")
    @Mapping(expression = "java(getBookings(entity))", target = "bookings")
    @Mapping(expression = "java(getLeaderboardPlace(entity))", target = "leaderboardPlace")
    @Mapping(target = "password", ignore = true)
    abstract public UserDto toDto(User entity);

    abstract public List<UserDto> toDtoList(List<User> user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "leaderboardPlace", ignore = true)
    @Mapping(expression = "java(getEncodedPassword(dto))", target = "password")
    abstract public User toEntity(UserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "birthDate", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "leaderboardPlace", ignore = true)
    abstract public User update(@MappingTarget User entity, UserDto dto);

    // TODO: getRooms, getBookings, getLeaderboardPlace functions

    String getEncodedPassword(UserDto dto) {
        return passwordEncoder.encode(dto.getPassword());
    }
}
