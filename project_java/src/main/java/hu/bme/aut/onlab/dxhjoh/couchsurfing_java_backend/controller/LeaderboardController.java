package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.controller;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.LeaderboardRole;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.exception.CouchsurfingRuntimeException;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.data_projection_interface.LeaderboardScoreProjection;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.LeaderboardRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration.LeaderboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @GetMapping
    public ResponseEntity<List<LeaderboardScoreProjection>> getLeaderboardScores(@RequestParam String role) {
        if (Objects.equals(role, "tenant")) {
            return ResponseEntity.ok(leaderboardService.getScores(LeaderboardRole.TENANT));
        } else if (Objects.equals(role, "owner")) {
            return ResponseEntity.ok(leaderboardService.getScores(LeaderboardRole.OWNER));
        }

        throw new CouchsurfingRuntimeException("The query parameter has to be either 'tenant' or 'owner'.");
    }

    @PostMapping
    public ResponseEntity<Long> createLeaderboardScore(@RequestParam String role, @RequestBody LeaderboardRequest lbReq) {
        if (Objects.equals(role, "tenant")) {
            return ResponseEntity.ok(leaderboardService.createTenantScore(lbReq));
        } else if (Objects.equals(role, "owner")) {
            return ResponseEntity.ok(leaderboardService.createOwnerScore(lbReq));
        }

        throw new CouchsurfingRuntimeException("The query parameter has to be either 'tenant' or 'owner'.");
    }
}
