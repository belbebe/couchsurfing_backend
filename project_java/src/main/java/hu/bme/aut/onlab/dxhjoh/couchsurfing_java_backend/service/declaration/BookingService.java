package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.BookingRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.BookingResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingReq);

    List<BookingResponse> getAllBookingsForTenant(Long tenantId);

    List<BookingResponse> getAllBookingsForOwner(Long ownerId);

    void deleteBooking(Long tenantId, Long bookingId);
}
