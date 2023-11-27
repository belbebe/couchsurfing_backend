package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.data_projection_interface;

public interface LeaderboardScoreProjection {
    Integer getUserId();
    String getFullName();
    String getUsername();
    Float getScore();
}
