package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginEmailRequest {
    private String email;

    private String password;
}
