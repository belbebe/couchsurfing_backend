package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Room;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.RoomRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.RoomResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {

    RoomResponse createRoom(RoomRequest roomReq);

    List<RoomResponse> getAllRooms();

    RoomResponse getRoomById(Long id);

    RoomResponse updateRoom(Long id, RoomRequest roomReq);

    void deleteRoom(Long id);

    //void assignBooking(Long bookingId);
}
