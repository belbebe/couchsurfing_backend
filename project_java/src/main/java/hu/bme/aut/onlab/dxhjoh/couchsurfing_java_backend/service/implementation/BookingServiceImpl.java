package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.implementation;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.exception.BookingAnAlreadyBookedRoomException;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.exception.BookingOwnRoomException;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper.BookingMapper;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Booking;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Room;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.BookingRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.RoomRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.UserRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.BookingRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.BookingResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration.BookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    @Override
    public BookingResponse createBooking(BookingRequest bookingReq) {
        User user = userRepository.findById(bookingReq.getRenterUserId()).orElseThrow(() -> new EntityNotFoundException("User entity not found."));
        Room room = roomRepository.findById((long) bookingReq.getRoomId()).orElseThrow(() -> new EntityNotFoundException("Room entity not found."));

        if (bookingReq.getRenterUserId() == room.getOwnerId()) {
            throw new BookingOwnRoomException("Room to be booked is the user's own room.");
        }

        List<Booking> bookingsForTheRoom = bookingRepository.findAll();
        for (Booking b : bookingsForTheRoom) {
            if (Objects.equals(b.getRoomId(), room.getId()) &&
                    (b.getStartDate().isBefore(bookingReq.getEndDate()) &&
                            bookingReq.getStartDate().isBefore(b.getEndDate()))) {
                throw new BookingAnAlreadyBookedRoomException("This room is booked during this time period.");
            }
        }

        Booking booking = bookingMapper.toEntity(bookingReq);
        booking.assignUser(user);
        booking.assignRoom(room);
        return bookingMapper.toResponse(bookingRepository.save(booking));
    }

    @Override
    public List<BookingResponse> getAllBookingsForTenant(Long tenantId) {
        return bookingMapper.toResponseList(bookingRepository.findByTenantId(tenantId));
    }

    @Override
    public List<BookingResponse> getAllBookingsForOwner(Long ownerId) {
        return bookingMapper.toResponseList(bookingRepository.findByOwnerId(ownerId));
    }

    @Override
    public void deleteBooking(Long tenantId, Long bookingId) {
    }
}
