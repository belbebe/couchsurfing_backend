package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomId(Long id);

    List<Booking> findByTenantId(Long id);

    @Query(value = "SELECT booking_id, tenant_id, booking.room_id, start_date, end_date, payment_method, num_of_guests," +
            "additional_notes, pay_with_chores, total_price, booking.currency" +
            " FROM booking JOIN room ON booking.room_id = room.room_id WHERE room.owner_id = ?1", nativeQuery = true)
    List<Booking> findByOwnerId(Long id);

    @Query(value = "SELECT booking_id FROM booking JOIN room ON booking.room_id = room.room_id" +
            " WHERE room.owner_id = ?2 AND booking.tenant_id = ?1", nativeQuery = true)
    List<Long> findBookingIdByTenantIdAndOwnerId(Long tenantId, Long ownerId);
}
