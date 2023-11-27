package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.LeaderboardRole;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.data_projection_interface.LeaderboardScoreProjection;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.LeaderboardRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeaderboardService {
    List<LeaderboardScoreProjection> getScores(LeaderboardRole role);

    Long createTenantScore(LeaderboardRequest lbReq);

    Long createOwnerScore(LeaderboardRequest lbReq);
}
