package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Room;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.RoomRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.UserRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.RoomResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.UserResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { BookingMapper.class })
public interface RoomMapper {
    @Mapping(source = "user", target = "userId", qualifiedByName = "UserToUserId")
    RoomResponse toResponse(Room entity);

    List<RoomResponse> toResponseList(List<Room> entity);

    Room toEntity(RoomRequest request);

    Room update(@MappingTarget Room entity, RoomRequest request);

    @Named("UserToUserId")
    public static int userToUserId(User user) {
        return user.getId().intValue();
    }
}
