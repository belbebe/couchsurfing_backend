package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Leaderboard;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.data_projection_interface.LeaderboardScoreProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    @Query(value="SELECT lb.user_id AS userId, u.full_name AS fullName, u.user_name AS userName, AVG(lb.host_score) AS score FROM " +
            "leader_board AS lb JOIN couchsurfing_user u ON lb.user_id = u.user_id WHERE lb.host_score NOTNULL " +
            "AND lb.host_score != 0 GROUP BY u.full_name, lb.user_id, u.user_name ORDER BY score DESC ", nativeQuery = true)
    List<LeaderboardScoreProjection> getOwnerScore();

    @Query(value="SELECT lb.user_id AS userId, u.full_name AS fullName, u.user_name AS userName, AVG(lb.tenant_score) AS score FROM " +
            "leader_board AS lb JOIN couchsurfing_user u ON lb.user_id = u.user_id WHERE lb.tenant_score NOTNULL " +
            "AND lb.tenant_score != 0 GROUP BY u.full_name, lb.user_id, u.user_name ORDER BY score DESC ", nativeQuery = true)
    List<LeaderboardScoreProjection> getTenantScore();
}
