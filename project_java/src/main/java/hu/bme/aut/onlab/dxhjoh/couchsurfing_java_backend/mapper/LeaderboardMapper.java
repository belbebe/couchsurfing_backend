package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Leaderboard;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.LeaderboardRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.LeaderboardResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LeaderboardMapper {
    @Mapping(source = "user", target = "userId", qualifiedByName = "UserToUserId")
    LeaderboardResponse toResponse(Leaderboard entity);

    List<LeaderboardResponse> toResponseList(List<Leaderboard> entity);

    Leaderboard toEntity(LeaderboardRequest request);

    Leaderboard update(@MappingTarget Leaderboard entity, LeaderboardRequest request);

    @Named("UserToUserId")
    public static int userToUserId(User user) {
        return user.getId().intValue();
    }
}
