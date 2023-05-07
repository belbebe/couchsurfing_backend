package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {
    private String fullName;

    private String username;

    private LocalDate birthDate;

    private String email;

    private String phone;

    private String password;
}
