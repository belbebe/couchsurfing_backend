package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDto {

    private int id;
    private String fullName;
    private String username;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String password;
    private Set<RoomDto> rooms;
    private Set<BookingDto> bookings;
    private LeaderboardDto leaderboardPlace;
}
