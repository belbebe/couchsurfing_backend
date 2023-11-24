package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.controller;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.BookingRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.BookingResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/owner/{id}")
    public ResponseEntity<List<BookingResponse>> getAllBookingsForOwner(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getAllBookingsForOwner(id));
    }

    @GetMapping("/tenant/{id}")
    public ResponseEntity<List<BookingResponse>> getAllBookingsForTenant(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getAllBookingsForTenant(id));
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingReq) {
        return ResponseEntity.ok(bookingService.createBooking(bookingReq));
    }
}
