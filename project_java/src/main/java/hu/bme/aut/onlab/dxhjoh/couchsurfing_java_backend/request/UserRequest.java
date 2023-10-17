package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.validation.EmailValidation;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {
    private String fullName;

    private String username;

    private LocalDate birthDate;

    @EmailValidation
    private String email;

    private String phone;

    private String password;
}
