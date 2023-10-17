package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private long timeout;
    private String tokenType;
    private List<String> roles;
}
