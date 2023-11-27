package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LeaderboardRequest {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer ownUserId;
    private Integer tenantScore;
    private Integer hostScore;
}
