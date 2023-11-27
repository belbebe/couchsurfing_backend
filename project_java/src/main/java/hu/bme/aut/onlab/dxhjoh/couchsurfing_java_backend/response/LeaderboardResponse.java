package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response;

import lombok.Data;

@Data
public class LeaderboardResponse {

    private int userId;
    private String fullName;
    private String username;
    private Float score;

    // TODO: image handling
}
