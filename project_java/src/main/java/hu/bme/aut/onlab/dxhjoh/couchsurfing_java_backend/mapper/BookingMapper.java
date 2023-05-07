package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Booking;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Room;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.BookingRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.RoomRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.BookingResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.RoomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {
    BookingResponse toResponse(Booking entity);

    List<BookingResponse> toResponseList(List<Booking> entity);

    Booking toEntity(BookingRequest request);

    Booking update(@MappingTarget Booking entity, BookingRequest request);
}
