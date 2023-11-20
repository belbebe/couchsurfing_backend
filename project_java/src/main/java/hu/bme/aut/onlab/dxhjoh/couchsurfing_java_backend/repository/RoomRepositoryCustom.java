package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Room;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.AccommodationRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoomRepositoryCustom {
    List<Room> findRoomsBasedOnSearchConditions(AccommodationRequest accReq);
}
