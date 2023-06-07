package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.implementation;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper.RoomMapper;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Room;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.RoomRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.UserRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.RoomRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.RoomResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    private final UserRepository userRepository;

    @Override
    public RoomResponse createRoom(RoomRequest roomReq) {
        User user = userRepository.findById(roomReq.getUserId()).orElseThrow(() -> new EntityNotFoundException("User entity not found."));
        Room room = roomMapper.toEntity(roomReq);
        room.assignUser(user);
        log.info("\n\n\n\n\n\n\n\n\n\n"+room.getUser().getEmail());
        return roomMapper.toResponse(roomRepository.save(room));
    }

    @Override
    public List<RoomResponse> getAllRooms() {
        return roomMapper.toResponseList(roomRepository.findAll());
    }

    @Override
    public RoomResponse getRoomById(Long id) {
        return roomMapper.toResponse(roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room entity not found.")));
    }

    @Override
    public RoomResponse updateRoom(Long id, RoomRequest roomReq) {
        return roomMapper.toResponse(roomMapper.update(roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Room entity not found.")), roomReq));
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    /*
    @Override
    public void assignBooking(Long bookingId) {
        userRepository.
    }
     */
}
