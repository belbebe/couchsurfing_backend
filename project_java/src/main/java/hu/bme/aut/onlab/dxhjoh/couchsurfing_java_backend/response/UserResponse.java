package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Booking;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Leaderboard;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Room;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserResponse {
    private int id;
    private String fullName;

    private String username;

    private LocalDate birthDate;

    private String email;

    private String phone;
    //private String imageUrl;

    private List<Room> rooms;

    private List<Booking> bookings;
}
