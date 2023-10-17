package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response;

import lombok.Data;

@Data
public class LeaderboardResponse {

    private int id;
    private int userId;
    private String fullName;
    private String username;
    private int score;
    //private String imageUrl;
}
