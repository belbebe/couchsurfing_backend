package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.dto_deprecated.UserDto;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.UserRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { BookingMapper.class, RoomMapper.class })
public interface UserMapper {

    UserResponse toResponse(User entity);

    List<UserResponse> toResponseList(List<User> entity);

    User toEntity(UserRequest request);

    User update(@MappingTarget User entity, UserRequest request);
}
