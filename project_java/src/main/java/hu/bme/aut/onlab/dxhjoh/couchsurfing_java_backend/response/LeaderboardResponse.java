package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response;

import lombok.Data;

import java.util.Date;

@Data
public class LeaderboardResponse {

    private int id;
    private String fullName;
    private String username;
    private int score;
    //private String imageUrl;
}
