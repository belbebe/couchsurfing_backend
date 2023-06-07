package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.controller;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.RoomRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.RoomResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAll() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomRequest roomReq) {
        return ResponseEntity.ok(roomService.createRoom(roomReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateById(@PathVariable Long id, @RequestBody RoomRequest roomReq) {
        return ResponseEntity.ok(roomService.updateRoom(id, roomReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Delete successful.");
    }
}
